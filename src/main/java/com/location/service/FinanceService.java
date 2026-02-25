import java.util.List;

public class FinanceService {

    // --- LOGIQUE DE CALCUL DES PRIX ---
    public Invoice generateInvoice(RentalRecord record) {
        Invoice invoice = new Invoice();

        // 1. Pricing Engine
        invoice.setBasePrice((record.getHourlyRate() * record.getPlannedDuration()) + record.getOptionsPrice());

        // 2. Penalty Logic : Retard (20% du tarif horaire par heure sup)
        if (record.getActualDuration() > record.getPlannedDuration()) {
            int extraHours = record.getActualDuration() - record.getPlannedDuration();
            invoice.setPenaltyLate(extraHours * (record.getHourlyRate() * 1.20));
        }

        // 3. Penalty Logic : Carburant (Prix + 30% de markup)
        invoice.setPenaltyFuel(record.getFuelMissingLiters() * (record.getFuelPricePerLiter() * 1.30));

        // Total
        invoice.setTotalAmount(invoice.getBasePrice() + invoice.getPenaltyLate() + invoice.getPenaltyFuel() + record.getDamageCosts());

        return invoice;
    }

    // --- ADMIN DASHBOARD : REPORTING ---
    public void generateRevenueReport(List<Invoice> allInvoices) {
        double totalRevenue = 0;
        for (Invoice i : allInvoices) {
            totalRevenue += i.getTotalAmount();
        }
        System.out.println("Rapport Financier :");
        System.out.println("Revenu Total : " + totalRevenue + " €");
        System.out.println("Nombre de transactions : " + allInvoices.size());
    }
}