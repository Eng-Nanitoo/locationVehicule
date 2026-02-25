package com.location.dto;

import com.location.model.enums.VehicleStatus;

public class StatusUpdateRequest {
    private VehicleStatus status;
    private String reason;

    public StatusUpdateRequest() {}

    public VehicleStatus getStatus() { return status; }
    public void setStatus(VehicleStatus status) { this.status = status; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
