package com.location.model;



import com.location.model.enums.ContractStatus;
import java.time.LocalDateTime;
import java.util.List;

public class Contract {
    private Long id;
    private Long clientId;
    private Vehicule vehicule;


    private int startOdometer;
    private double startFuelLevel;
    private LocalDateTime departureDate;


    private int endOdometer;
    private int Odometer;
    private List<String> damages;
    private LocalDateTime returnDate;

    private ContractStatus status;


    public Contract() {
        this.status = ContractStatus.RESERVED;
    }


    public Contract(Long id, Long clientId, Vehicule vehicule,int Odometer) {
        this();
        this.id = id;
        this.Odometer = Odometer;
        this.clientId = clientId;
        this.vehicule = vehicule;
    }



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public Vehicule getVehicule() { return vehicule; }
    public void setVehicule(Vehicule vehicule) { this.vehicule = vehicule; }

    public int getStartOdometer() { return startOdometer; }
    public void setStartOdometer(int startOdometer) { this.startOdometer = startOdometer; }

    public double getStartFuelLevel() { return startFuelLevel; }
    public void setStartFuelLevel(double startFuelLevel) { this.startFuelLevel = startFuelLevel; }

    public LocalDateTime getDepartureDate() { return departureDate; }
    public void setDepartureDate(LocalDateTime departureDate) { this.departureDate = departureDate; }

    public int getEndOdometer() { return endOdometer; }
    public void setEndOdometer(int endOdometer) { this.endOdometer = endOdometer; }

    public int getOdometer() { return Odometer; }
    public void setOdometer(int Odometer) { this.Odometer = Odometer; }

    public List<String> getDamages() { return damages; }
    public void setDamages(List<String> damages) { this.damages = damages; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public ContractStatus getStatus() { return status; }
    public void setStatus(ContractStatus status) { this.status = status; }

}
