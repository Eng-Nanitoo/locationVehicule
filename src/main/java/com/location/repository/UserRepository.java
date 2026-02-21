package com.location.repository;

import com.location.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {
    // Shared data across the app
    private static final Map<String, User> users = new HashMap<>();

    static {
        // Requirement: Default Admin
        users.put("admin", new User("admin", "admin", "Administrateur"));
    }

    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }

    public boolean exists(String email) {
        return users.containsKey(email);
    }
}