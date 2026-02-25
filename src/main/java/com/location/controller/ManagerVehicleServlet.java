package com.location.controller;

import com.location.model.Vehicule;
import com.location.model.enums.VehicleStatus;
import com.location.service.VehiculeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/manager/vehicules")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ManagerVehicleServlet {

    private final VehiculeService vehicleService = new VehiculeService();

    @PUT
    @Path("/{vin}/available")
    public Response markAvailable(@PathParam("vin") String vin) {
        vehicleService.updateStatus(vin, VehicleStatus.AVAILABLE, "Disponible");
        return Response.ok("{\"message\": \"Statut mis à jour.\"}").build();
    }

    @PUT
    @Path("/{vin}/maintenance")
    public Response markMaintenance(@PathParam("vin") String vin) {
        vehicleService.updateStatus(vin, VehicleStatus.MAINTENANCE, "Maintenance");
        return Response.ok("{\"message\": \"Statut mis à jour.\"}").build();
    }

    @PUT
    @Path("/{vin}/accidente")
    public Response markAccidente(@PathParam("vin") String vin) {
        vehicleService.updateStatus(vin, VehicleStatus.ACCIDENTED, "Accident");
        return Response.ok("{\"message\": \"Statut mis à jour.\"}").build();
    }
}