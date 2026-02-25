package com.location.repository;

import com.location.model.User;
import java.util.Optional;

public class UserRepository {
    public void save(User user) {
        MockDatabase.users.put(user.getEmail(), user);
    }
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(MockDatabase.users.get(email));
    }
    public boolean exists(String email) {
        return MockDatabase.users.containsKey(email);
    }
}