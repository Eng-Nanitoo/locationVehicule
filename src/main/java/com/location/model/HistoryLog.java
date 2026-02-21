package com.location.model;
import java.time.LocalDateTime;
public class HistoryLog {
    private Long id;
    private LocalDateTime timestamp;
    private String vehicleVin;
    private Long clientId;
    private String eventType; // ex: "MAINTENANCE", "RETURN", "DAMAGE"
    private String description;

    public HistoryLog() { this.timestamp = LocalDateTime.now(); }
    public HistoryLog(String vehicleVin,String description){
        this.vehicleVin=vehicleVin;
        this.description=description;
    }
    public HistoryLog(String vehicleVin, Long clientId, String eventType, String description) {
        this();
        this.vehicleVin = vehicleVin;
        this.clientId = clientId;
        this.eventType = eventType;
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
