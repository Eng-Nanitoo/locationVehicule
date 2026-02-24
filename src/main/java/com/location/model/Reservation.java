package com.location.model;

import com.location.model.enums.Extra;
import com.location.model.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class Reservation {

    private UUID id;
    private UUID clientId;
    private UUID vehicleId;
    private String vehicleCategory;

    private LocalDateTime departureDateTime;
    private LocalDateTime returnDateTime;

    private String departureAgencyId;
    private String returnAgencyId;

    private List<Extra> extras;

    private ReservationStatus status;

    private double totalCost;
    private LocalDateTime createdAt;

    public Reservation() {
        this.id = UUID.randomUUID();
        this.status = ReservationStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public Reservation(UUID clientId, String vehicleCategory,
                       LocalDateTime departureDateTime, LocalDateTime returnDateTime,
                       String departureAgencyId, String returnAgencyId,
                       List<Extra> extras) {
        this.id = UUID.randomUUID();
        this.clientId = clientId;
        this.vehicleCategory = vehicleCategory;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.departureAgencyId = departureAgencyId;
        this.returnAgencyId = returnAgencyId;
        this.extras = extras;
        this.status = ReservationStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isCancellable() {
        return status == ReservationStatus.PENDING || status == ReservationStatus.CONFIRMED &&
                LocalDateTime.now().isBefore(departureDateTime.minusHours(48));
    }

    public long getDurationInDays() {
        return java.time.Duration.between(departureDateTime, returnDateTime).toDays();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getClientId() { return clientId; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }

    public UUID getVehicleId() { return vehicleId; }
    public void setVehicleId(UUID vehicleId) { this.vehicleId = vehicleId; }

    public String getVehicleCategory() { return vehicleCategory; }
    public void setVehicleCategory(String vehicleCategory) { this.vehicleCategory = vehicleCategory; }

    public LocalDateTime getDepartureDateTime() { return departureDateTime; }
    public void setDepartureDateTime(LocalDateTime departureDateTime) { this.departureDateTime = departureDateTime; }

    public LocalDateTime getReturnDateTime() { return returnDateTime; }
    public void setReturnDateTime(LocalDateTime returnDateTime) { this.returnDateTime = returnDateTime; }

    public String getDepartureAgencyId() { return departureAgencyId; }
    public void setDepartureAgencyId(String departureAgencyId) { this.departureAgencyId = departureAgencyId; }

    public String getReturnAgencyId() { return returnAgencyId; }
    public void setReturnAgencyId(String returnAgencyId) { this.returnAgencyId = returnAgencyId; }

    public List<Extra> getExtras() { return extras; }
    public void setExtras(List<Extra> extras) { this.extras = extras; }

    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}