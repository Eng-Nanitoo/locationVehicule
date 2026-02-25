public class RentalRecord {
    double hourlyRate;
    private int plannedDuration;
    private int actualDuration;
    private double fuelMissingLiters;
    private double fuelPricePerLiter = 1.5;
    private double damageCosts;
    private double optionsPrice;

    // Constructeur complet
    public RentalRecord(double hourlyRate, int plannedDuration, int actualDuration,
                        double fuelMissingLiters, double damageCosts, double optionsPrice) {
        this.hourlyRate = hourlyRate;
        this.plannedDuration = plannedDuration;
        this.actualDuration = actualDuration;
        this.fuelMissingLiters = fuelMissingLiters;
        this.damageCosts = damageCosts;
        this.optionsPrice = optionsPrice;
    }

    // Getters et Setters
    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public int getPlannedDuration() { return plannedDuration; }
    public void setPlannedDuration(int plannedDuration) { this.plannedDuration = plannedDuration; }

    public int getActualDuration() { return actualDuration; }
    public void setActualDuration(int actualDuration) { this.actualDuration = actualDuration; }

    public double getFuelMissingLiters() { return fuelMissingLiters; }
    public void setFuelMissingLiters(double fuelMissingLiters) { this.fuelMissingLiters = fuelMissingLiters; }

    public double getFuelPricePerLiter() { return fuelPricePerLiter; }

    public double getDamageCosts() { return damageCosts; }
    public void setDamageCosts(double damageCosts) { this.damageCosts = damageCosts; }

    public double getOptionsPrice() { return optionsPrice; }
    public void setOptionsPrice(double optionsPrice) { this.optionsPrice = optionsPrice; }
}