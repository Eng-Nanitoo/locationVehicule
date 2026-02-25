package com.location.service;

import com.location.model.User;
import com.location.model.Agency;
import com.location.repository.MockDatabase;
import java.util.ArrayList;
import java.util.List;

public class ManagerService {

    public List<User> getAllManagers() {
        return new ArrayList<>(MockDatabase.users.values());
    }

    public User getManagerByEmail(String email) {
        User u = MockDatabase.users.get(email);
        if (u == null) throw new RuntimeException("Manager introuvable.");
        return u;
    }

    public User createManager(User manager) {
        MockDatabase.users.put(manager.getEmail(), manager);
        return manager;
    }

    public User updateManager(String email, User updated) {
        return getManagerByEmail(email);
    }

    public void deleteManager(String email) {
        MockDatabase.users.remove(email);
    }

    public Agency assignManagerToAgency(String email, String agencyId) {
        return null;
    }

    public void unassignManager(String email) {}
}