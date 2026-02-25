package com.location.config;

import com.location.model.Contract;
import com.location.model.HistoryLog;
import com.location.model.User;
import com.location.model.Vehicule;
//import com.location.model.Vehicle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
    private static DataStore instance;

    // Use ConcurrentHashMap for thread safety in a web environment
    private final Map<String, User> users = new ConcurrentHashMap<>();
//    private final Map<String, Vehicle> vehicles = new ConcurrentHashMap<>();

    private DataStore() {
        // Requirement: Default Administrator
        users.put("admin", new User("admin@admin.com", "admin", "Administrateur"));
    }

    public static synchronized DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public Map<String, User> getUsers() {
        return users;
    }

//    public Map<String, Vehicle> getVehicles() {
//        return vehicles;
//    }
}