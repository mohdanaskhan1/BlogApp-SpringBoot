package com.example.blogapp.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
//
//    private final Key key = Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username, long expiryMinutes) {
        Date date = new Date();
        Date expiry = new Date(System.currentTimeMillis() + expiryMinutes * 60 * 1000);

        return Jwts.builder()
                .subject(username)
                .issuedAt(date)
                .expiration(expiry)
                .signWith(key)
                .compact();
    }

    public String validateAndExtractUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}
