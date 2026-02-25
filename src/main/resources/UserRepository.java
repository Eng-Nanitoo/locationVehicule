package com.location.repository;

import com.location.model.User;
import java.util.*;

public class UserRepository {

    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("admin", new User("admin", "admin", "Administrateur"));
    }

    public void save(User user) { users.put(user.getEmail(), user); }
    public Optional<User> findByEmail(String email) { return Optional.ofNullable(users.get(email)); }
    public boolean exists(String email) { return users.containsKey(email); }
    public List<User> findAll() { return new ArrayList<>(users.values()); }
    public void delete(String email) { users.remove(email); }
}