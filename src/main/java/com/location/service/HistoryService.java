package com.location.service;


import com.location.model.HistoryLog;
import com.location.repository.MockDatabase;


import java.util.List;
import java.util.stream.Collectors;


public class HistoryService {


    public List<HistoryLog> getLogsByVehicle(String vin) {
        return MockDatabase.history.stream()
                .filter(log -> log.getVehicleVin().equals(vin))
                .collect(Collectors.toList());
    }


    public List<HistoryLog> getLogsByClient(Long clientId) {
        return MockDatabase.history.stream()
                .filter(log -> log.getClientId() != null && log.getClientId().equals(clientId))
                .collect(Collectors.toList());
    }
}

