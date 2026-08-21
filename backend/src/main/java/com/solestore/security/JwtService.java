package com.solestore.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey key;
    private final long expirationMs;
    public JwtService(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration-ms}") long expirationMs) {
        if (secret == null || secret.length() < 32) throw new IllegalArgumentException("JWT_SECRET must contain at least 32 characters");
        this.key = Keys.hmacShaKeyFor(secret.getBytes(java.nio.charset.StandardCharsets.UTF_8)); this.expirationMs = expirationMs;
    }
    public String generateToken(UserDetails user) { Date now = new Date(); return Jwts.builder().subject(user.getUsername()).issuedAt(now).expiration(new Date(now.getTime() + expirationMs)).signWith(key).compact(); }
    public String extractUsername(String token) { return claims(token).getSubject(); }
    public boolean isValid(String token, UserDetails user) { try { Claims claims = claims(token); return claims.getSubject().equalsIgnoreCase(user.getUsername()) && claims.getExpiration().after(new Date()); } catch (RuntimeException exception) { return false; } }
    private Claims claims(String token) { return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload(); }
}