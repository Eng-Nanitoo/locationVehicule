public class Invoice {
    private double basePrice;
    private double penaltyLate;
    private double penaltyFuel;
    private double damageCosts; // Ajouté pour le détail de la facture
    private double totalAmount;

    // Constructeur vide (souvent utilisé par les frameworks comme Jackson/JSON)
    public Invoice() {}

    // Constructeur complet
    public Invoice(double basePrice, double penaltyLate, double penaltyFuel, double damageCosts, double totalAmount) {
        this.basePrice = basePrice;
        this.penaltyLate = penaltyLate;
        this.penaltyFuel = penaltyFuel;
        this.damageCosts = damageCosts;
        this.totalAmount = totalAmount;
    }

    // Getters et Setters
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public double getPenaltyLate() { return penaltyLate; }
    public void setPenaltyLate(double penaltyLate) { this.penaltyLate = penaltyLate; }

    public double getPenaltyFuel() { return penaltyFuel; }
    public void setPenaltyFuel(double penaltyFuel) { this.penaltyFuel = penaltyFuel; }

    public double getDamageCosts() { return damageCosts; }
    public void setDamageCosts(double damageCosts) { this.damageCosts = damageCosts; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}