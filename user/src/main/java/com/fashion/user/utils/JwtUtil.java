package com.fashion.user.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtUtil {

    @Value("${jwt.secret}") // Use environment-configured secret
    private String jwtSecret;

    @Value("${jwt.expiration}") // Define expiration time in properties (e.g., 3600000 ms = 1 hour)
    private long jwtExpiration;

    public Map<String, String> generateToken(String username) {
        Map<String, String> tokenMap = new HashMap<>();
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);

            String token = Jwts.builder()
                    .setSubject(username)
                    .setClaims(claims)
                    .setIssuer("FashionApp") // Add issuer
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                    .signWith(SignatureAlgorithm.HS256, jwtSecret) // Secure key
                    .compact();

            tokenMap.put("token", token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating token");
        }
        return tokenMap;
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
