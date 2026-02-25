package com.location.service;

import com.location.model.User;
import com.location.repository.MockDatabase;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class AuthService {

    private static final Key SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String authenticate(String email, String password) {
        User user = MockDatabase.users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            return Jwts.builder()
                    .setSubject(email)
                    .claim("role", user.getRole())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(SECRET)
                    .compact();
        }
        return null;
    }
}