package com.location.service;

import com.location.model.Vehicule;
import com.location.model.enums.VehicleStatus;
import com.location.repository.MockDatabase;
import java.util.ArrayList;
import java.util.List;

public class VehiculeService {

    public List<Vehicule> getAllVehicules() {
        return new ArrayList<>(MockDatabase.vehicles.values());
    }

    public Vehicule getVehiculeByVin(String vin) {
        Vehicule v = MockDatabase.vehicles.get(vin);
        if (v == null) throw new RuntimeException("Introuvable.");
        return v;
    }

    public Vehicule addVehicule(Vehicule v) {
        MockDatabase.vehicles.put(v.getVin(), v);
        return v;
    }

    public Vehicule updateVehicule(String vin, Vehicule updated) {
        Vehicule existing = getVehiculeByVin(vin);
        if (updated.getModel() != null) existing.setModel(updated.getModel());
        if (updated.getStatus() != null) existing.setStatus(updated.getStatus());
        return existing;
    }

    public void deleteVehicule(String vin) {
        MockDatabase.vehicles.remove(vin);
    }

    public Vehicule updateStatus(String vin, VehicleStatus status, String reason) {
        Vehicule v = MockDatabase.vehicles.get(vin);
        if (v != null) v.setStatus(status);
        return v;
    }
}