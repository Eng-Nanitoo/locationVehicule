package com.location.model;

import com.location.model.enums.VehicleStatus;

public class Vehicule {
    private long id;
    private String vin;
    private String model;
    private int lastOdometer;
    private VehicleStatus status;
    public Vehicule() {}

    public Vehicule(String vin, String model, int lastOdometer, VehicleStatus status) {
        this.vin = vin;
        this.model = model;
        this.lastOdometer = lastOdometer;
        this.status = status;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getLastOdometer() {
        return lastOdometer;
    }

    public void setLastOdometer(int lastOdometer) {
        this.lastOdometer = lastOdometer;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
}
