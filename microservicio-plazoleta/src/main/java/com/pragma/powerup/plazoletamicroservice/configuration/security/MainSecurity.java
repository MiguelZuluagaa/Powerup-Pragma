package com.pragma.powerup.plazoletamicroservice.configuration.security;

import com.pragma.powerup.plazoletamicroservice.configuration.security.jwt.JwtEntryPoint;
import com.pragma.powerup.plazoletamicroservice.configuration.security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.*;

@Configuration
@EnableWebSecurity
public class MainSecurity {

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests(requests -> requests
                        // Public endpoints
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/health").permitAll()
                        // Private endpoints
                        .requestMatchers("/order/getOrdersByStatus/**","/order/takeOrder/**","/order/markAsReady/**","/order/markAsFinished/").hasAnyRole(ROLE_EMPLOYEE)
                        .requestMatchers("/order/","/restaurant/getRestaurantsWithPagination/**","/dish/getDishesByCategory/**").hasAnyRole(ROLE_CUSTOMER)
                        .requestMatchers("/restaurant/","/restaurant/**","/category/**","/category/","/restaurant/delete/**").hasAnyRole(ROLE_ADMIN)
                        .requestMatchers("/dish/","/dish/**","/order/getReportOfOrdersCompleted/**","/order/getReportOfOrdersCompletedByEmployee/**").hasAnyRole(ROLE_OWNER)
                        //OTHERS ENDPOINTS NEED AUTHENTICATION
                        .anyRequest().authenticated()
                )
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
