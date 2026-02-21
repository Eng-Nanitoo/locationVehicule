package com.location.service;

import com.location.model.enums.VehicleStatus;
import com.location.model.Vehicule;
import com.location.repository.MockDatabase;



public class VehicleService {

    public void updateStatus(String vin, VehicleStatus status, String reason) {

        Vehicule v = MockDatabase.vehicles.get(vin);

        if (v != null) {
            v.setStatus(status);

            System.out.println("Véhicule " + vin + " mis à jour : " + status + " (Raison: " + reason + ")");
        } else {

            throw new RuntimeException("Véhicule avec le VIN " + vin + " introuvable.");
        }
    }
}