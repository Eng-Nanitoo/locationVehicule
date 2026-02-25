import com.location.model.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/finance")
public class FinanceServlet {

    // Simulation de base de données pour les rapports
    private static final List<Map<String, Object>> history = new ArrayList<>();

    @POST
    @Path("/calculate")

    public Response calculateInvoice(RentalRecord record) {
        double baseCost = (record.hourlyRate * record.getPlannedDuration()) + record.getOptionsPrice();

        int lateHours = Math.max(0, record.getActualDuration() - record.getPlannedDuration());
        double penaltyLate = lateHours * (record.hourlyRate * 1.20);

        double penaltyFuel = record.getFuelMissingLiters() * (1.80 * 1.30);

        double total = baseCost + penaltyLate + penaltyFuel + record.getDamageCosts();

        Map<String, Object> invoice = Map.of(
                "basePrice", baseCost,
                "penaltyLate", penaltyLate,
                "penaltyFuel", penaltyFuel,
                "damageCosts", record.getDamageCosts(),
                "totalAmount", total
        );

        history.add(invoice);

        return Response.ok(invoice).build();
    }

    @GET
    @Path("/dashboard")
    public Response getAdminReport() {
        double totalRevenue = history.stream()
                .mapToDouble(i -> (double) i.get("totalAmount"))
                .sum();

        Map<String, Object> report = Map.of(
                "totalInvoices", history.size(),
                "totalRevenue", totalRevenue,
                "currency", "EUR"
        );

        return Response.ok(report).build();
    }
}