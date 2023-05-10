package com.pragma.powerup.usermicroservice.configuration.security.jwt;


import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImpl usuarioDetailsService;

    private List<String> excludedPrefixes = Arrays.asList("/auth/**", "/swagger-ui/**", "/actuator/**", "/user/");
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(req);
        if (token != null && jwtProvider.validateToken(token)) {
            String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
            UserDetails userDetails = usuarioDetailsService.loadUserByUsername(nombreUsuario);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(req, res);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String currentRoute = request.getServletPath();
        for (String prefix : excludedPrefixes) {
            if (pathMatcher.matchStart(prefix, currentRoute)) {
                return true;
            }
        }
        return false;
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // return everything after "Bearer "
        }
        return null;
    }
}
