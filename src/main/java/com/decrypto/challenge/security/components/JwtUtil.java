package com.decrypto.challenge.security.components;

import com.decrypto.challenge.utils.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtUtil {

    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles =new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities){
            roles.add(grantedAuthority.getAuthority());
        }
        claims.put(Constants.ROLES_STRING, roles);
        claims.put(Constants.USERNAME_STRING,username);
        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME_1_HS))
                .signWith(SignatureAlgorithm.HS256, Constants.SECRET_KEY);
        return builder.compact();
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(Constants.USERNAME_STRING, String.class);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}