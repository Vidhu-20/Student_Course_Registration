package com.SpringSecurity.authservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 1. Requirement: The key must be a long string (at least 32 characters)
    private final String SECRET_STRING = "your_secret_key_must_be_very_long_at_least_32_chars_!!";
    
    // 2. Requirement: Convert string to a secure Key object for Java 17 compatibility
    private final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());

    public String generateToken(String username, String role) {
        try {
            // Normalize role to uppercase
            String normalizedRole = role == null ? "USER" : role.toUpperCase();
            String token = Jwts.builder()
                    .setSubject(username)
                    .claim("role", normalizedRole)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                    .signWith(SIGNING_KEY, SignatureAlgorithm.HS256) 
                    .compact();

            // This prints the token to your VS Code console as requested
            System.out.println("\n[AUTH SERVICE] Login successful for user: " + username);
            System.out.println("[AUTH SERVICE] Generated Token with role: " + normalizedRole + " - " + token + "\n");

            return token;
        } catch (Exception e) {
            // This will show exactly why it fails in your console if a 500 happens again
            System.err.println("JWT Generation Error: " + e.getMessage());
            e.printStackTrace();
            return "Error generating token";
        }
    }
    public String extractUsername(String token) {
    return extractAllClaims(token).getSubject();
}

public String extractRole(String token) {
    return extractAllClaims(token).get("role", String.class);
}

private io.jsonwebtoken.Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(SIGNING_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();
}

public boolean isTokenValid(String token) {
    try {
        extractAllClaims(token);
        return !isTokenExpired(token);
    } catch (Exception e) {
        System.err.println("[JWT UTIL] Token validation error: " + e.getMessage());
        return false;
    }
}

public boolean isTokenExpired(String token) {
    try {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    } catch (Exception e) {
        System.err.println("[JWT UTIL] Token expiration check failed: " + e.getMessage());
        return true;
    }
}

public Date extractExpiration(String token) {
    return extractAllClaims(token).getExpiration();
}
}