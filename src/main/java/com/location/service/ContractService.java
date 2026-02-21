package com.location.service;

import com.location.model.enums.VehicleStatus;
import com.location.model.HistoryLog;
import com.location.model.Vehicule; //
import com.location.model.Contract; //
import com.location.repository.MockDatabase;


import java.util.List;


public class ContractService {

    public void processReturn(Long id, int odo, List<String> damages) {

        Contract contract = MockDatabase.contracts.get(id);

        if (contract == null) {
            throw new RuntimeException("Contrat introuvable pour l'ID : " + id);
        }


        contract.setEndOdometer(odo);


        Vehicule v = contract.getVehicule();
        v.setLastOdometer(odo);


        if (damages != null && !damages.isEmpty()) {
            v.setStatus(VehicleStatus.MAINTENANCE);
        } else {
            v.setStatus(VehicleStatus.AVAILABLE);
        }

        // 5. Build history tracking
        MockDatabase.history.add(new HistoryLog(
                v.getVin(),
                contract.getClientId(),
                "RETURN",
                "Retour effectué. KM: " + odo + " | Dommages: " + (damages != null ? damages.size() : 0)
        ));
    }


            public void processDeparture(Long id, int odometer, int fuelLevel) {
                // 1. Récupération du contrat via l'ID (clé de la Map)
                Contract contract = MockDatabase.contracts.get(id);

                if (contract == null) {
                    throw new RuntimeException("Erreur : Contrat introuvable pour l'ID : " + id);
                }

                // 2. Mise à jour des informations de départ sur l'objet contrat
                contract.setStartOdometer(odometer);
                contract.setStartFuelLevel(fuelLevel);

                // 3. Mise à jour du véhicule associé
                Vehicule v = contract.getVehicule();
                if (v != null) {
                    v.setLastOdometer(odometer);
                    // On change le statut : le véhicule est maintenant loué
                    v.setStatus(VehicleStatus.IN_USE);
                }

                // 4. Enregistrement dans l'historique global
                MockDatabase.history.add(new HistoryLog(
                        v.getVin(),
                        contract.getClientId(),
                        "DEPARTURE",
                        "Départ validé. KM: " + odometer + " | Fuel: " + fuelLevel + "%"
                ));
            }
}

