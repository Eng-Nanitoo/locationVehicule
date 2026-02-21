package com.location.service;

import com.location.model.User;
import com.location.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

public class AuthService {
    private final UserRepository userRepository = new UserRepository();

    // Key must be static so the Filter can use the same key to validate
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String authenticate(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return Jwts.builder()
                    .setSubject(userOpt.get().getEmail())
                    .claim("role", userOpt.get().getRole())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                    .signWith(KEY)
                    .compact();
        }
        return null;
    }

    public Key getSecretKey() {
        return KEY;
    }
}