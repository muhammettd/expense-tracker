package com.project.expensetracker_api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.key}")
    private String secretKey;


        public String generateToken(String username) {
            Map<String, Object> claims = new HashMap<>();
            return createToken(claims, username);
        }

        public Boolean validateToken(String token, UserDetails userDetails) {
            String username = extractUser(token);
            Date expirationDate = extractExpiration(token);
            return userDetails.getUsername().equals(username) && !expirationDate.before(new Date());
        }

        private Date extractExpiration(String token) {
            Claims claims = Jwts
                    .parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getExpiration();
        }
        public String extractUser(String token) {
            Claims claims = Jwts
                    .parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        }

        private String createToken(Map<String, Object> claims, String userName) {
            return Jwts.builder()
                    .claims(claims)
                    .subject(userName)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                    .signWith(getSignKey())
                    .compact();
        }

        private SecretKey getSignKey() {
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);
        }





}
