package com.location.repository;

import com.location.model.enums.VehicleStatus;
import com.location.model.HistoryLog;
import com.location.model.Vehicule;
import com.location.model.Contract;
import com.location.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class MockDatabase {

    public static Map<String, Vehicule> vehicles = new HashMap<>();
    public static Map<String, Contract> contracts = new HashMap<>();
    public static List<HistoryLog> history = new ArrayList<>();
    public static Map<String, User> users = new HashMap<>();

    static {
        users.put("admin", new User("admin", "admin", "Administrateur"));
        addVehicle("VIN001", "Toyota Corolla", VehicleStatus.AVAILABLE);
        addVehicle("VIN002", "BMW X5", VehicleStatus.AVAILABLE);
    }

    public static void addVehicle(String vin, String model, VehicleStatus status) {
        Vehicule newVehicule = new Vehicule(vin, model, 1, status);
        vehicles.put(vin, newVehicule);
    }
}