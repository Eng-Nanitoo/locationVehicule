package com.location.config;

import com.location.model.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
	
    private static final Map<String, User> users = new ConcurrentHashMap<>();
    private static final Map<String, Vehicle> vehicles = new ConcurrentHashMap<>();
    private static final Map<String, Reservation> reservations = new ConcurrentHashMap<>();

    static {
        User admin = new User("admin", "admin", "ADMIN");
        users.put("admin", admin);
    }

    public static Map<String, User> getUsers() { return users; }
    public static Map<String, Vehicle> getVehicles() { return vehicles; }
    public static Map<String, Reservation> getReservations() { return reservations; }
}