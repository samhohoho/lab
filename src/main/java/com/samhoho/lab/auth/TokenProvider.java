package com.samhoho.lab.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class TokenProvider {
    private String jwtSecret;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode("jwtSecretjwtSecretjwtSecretjwtSecretjwtSecretjwtSecretjwtSecretjwtSecretjwtSecretjwtSecretjwtSecretjwtSecretjwtSecretjwtSecret"));
    }

    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        String jwt = Jwts.builder()
                .subject(username)
                .signWith(key())
                .compact();
        return jwt;
    }
}
