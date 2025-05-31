package com.user_service.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "7fa00902b281d16f7dc37aa7c8ca93c53f062e5a182e72f1afcada769be6e984e20b2d4e8b5e60c4645939c8bf130caed930286896cefa4634fa079cdfd1855efc3ca2285c974f2c88ecfc0a3a0ff475a24a4b0770b779c562cd845d525cb46d3abcd2b48313081df36520ba7c8a372753a038e0dbf12afe0c44683162becce1e18ab14d23a33f17bf9d7da24348abdbfa5971380de03f0919fee6a8bd9ab577ce010081e7c89a0f2fcae98c688bdb9f0ed57b83a4b0a249201b77600edf72f0e13a68c0fc27f979670468fd11f354e7a31bba4150ec4a4f638d4941848f4fb844b9a7c5d8b42c7f8ce90ba4201619d4ccc4e7b25944beb1816afaad84e9de0b";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }
}
