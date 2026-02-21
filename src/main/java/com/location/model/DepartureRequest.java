package com.location.model;


public class DepartureRequest {
    private int odometer;
    private int fuelLevel;

    // Constructeur vide (nécessaire pour la lecture JSON/Jakarta)
    public DepartureRequest() {}

    public DepartureRequest(int odometer, int fuelLevel) {
        this.odometer = odometer;
        this.fuelLevel = fuelLevel;
    }

    // Getters et Setters indispensables
    public int getStartOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }
}