package com.location.model;

import java.util.List;


public class ReturnRequest {
    private int endOdometer;
    private List<String> damages;

    // Constructeur vide pour le décodage JSON
    public ReturnRequest() {}

    public ReturnRequest(int endOdometer, List<String> damages) {
        this.endOdometer = endOdometer;
        this.damages = damages;
    }

    // Getters et Setters
    public int getEndOdometer() {
        return endOdometer;
    }

    public void setEndOdometer(int endOdometer) {
        this.endOdometer = endOdometer;
    }

    public List<String> getDamages() {
        return damages;
    }

    public void setDamages(List<String> damages) {
        this.damages = damages;
    }
}