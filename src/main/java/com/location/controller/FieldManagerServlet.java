package com.location.controller;

import com.location.model.*;
import com.location.model.enums.VehicleStatus;
import com.location.repository.MockDatabase;
import com.location.service.ContractService;
import com.location.service.HistoryService;
import com.location.service.VehicleService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/field-manager")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FieldManagerServlet {


    private final ContractService contractService = new ContractService();

    private final VehicleService vehicleService = new VehicleService();

    private final HistoryService historyService= new HistoryService();



    @POST
    @Path("/contracts/{id}/departure")

    public Response departureCheck(@PathParam("id") Long id, DepartureRequest req) {

        Contract contract = MockDatabase.contracts.get(id);

        if (contract == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Contrat introuvable\"}").build();
        }

        contract.setStartOdometer(req.getStartOdometer());
        contract.setStartFuelLevel(req.getFuelLevel());

        MockDatabase.history.add(new HistoryLog(contract.getVehicule().getVin(),"Véhicule en service"));

        return Response.ok("{\"message\": \"Départ validé : Véhicule en service.\"}").build();
    }

    @POST
    @Path("/contracts/{id}/return")

    public Response returnCheck(@PathParam("id") Long id, ReturnRequest req) {
        Contract contract = MockDatabase.contracts.get(id);

        if (contract == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Contrat introuvable\"}").build();
        }

        contract.setEndOdometer(req.getEndOdometer());

        Vehicule v = MockDatabase.vehicles.get(contract.getVehicule().getVin());
        if (v != null) {
            v.setStatus(VehicleStatus.AVAILABLE);
        }

        MockDatabase.history.add(new HistoryLog(contract.getVehicule().getVin(), "Retour traité - Dégâts: " + req.getDamages()));

        return Response.ok("{\"message\": \"Retour traité : État du véhicule mis à jour.\"}").build();
    }


    @PUT
    @Path("/vehicles/{vin}/block")

    public Response blockVehicle(@PathParam("vin") String vin, @QueryParam("reason") String reason) {
        Vehicule v = MockDatabase.vehicles.get(vin);

        if (v == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Véhicule introuvable\"}").build();
        }

        v.setStatus(VehicleStatus.MAINTENANCE);
        MockDatabase.history.add(new HistoryLog(vin, "Bloqué pour maintenance: " + reason));

        return Response.ok("{\"message\": \"Véhicule bloqué pour : " + reason + "\"}").build();
    }


    @GET
    @Path("/history/vehicle/{vin}")

    public Response getVehicleHistory(@PathParam("vin") String vin) {
        // Filtrage de la liste statique en mémoire
        List<HistoryLog> filteredHistory = MockDatabase.history.stream()
                .filter(log -> log.getVehicleVin().equals(vin))
                .collect(Collectors.toList());

        return Response.ok(filteredHistory).build();
    }
}
