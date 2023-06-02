package com.pragma.powerup.messengermicroservice.configuration.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token bad formatted");
        } catch (UnsupportedJwtException e) {
            logger.error("token not supported");
        } catch (ExpiredJwtException e) {
            logger.error("token expired");
        } catch (IllegalArgumentException e) {
            logger.error("token empty");
        } catch (SignatureException e) {
            logger.error("fail in signature");
        }
        return false;
    }

}
