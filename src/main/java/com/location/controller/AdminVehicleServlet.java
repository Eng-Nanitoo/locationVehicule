package com.location.controller;

import com.location.model.Vehicule;
import com.location.service.VehiculeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Admin endpoints for Vehicle CRUD.
 * All routes under /api/admin/vehicles — protected by AuthFilter (role: Administrateur).
 */
@Path("/admin/vehicles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminVehicleServlet {

    private final VehiculeService vehicleService = new VehiculeService();

    // ── GET ALL VEHICLES ─────────────────────────────────────
    @GET
    public Response getAllVehicules() {
        List<Vehicule> vehicles = vehicleService.getAllVehicules();
        return Response.ok(vehicles).build();
    }

    // ── GET VEHICLE BY VIN ───────────────────────────────────
    @GET
    @Path("/{vin}")
    public Response getVehicle(@PathParam("vin") String vin) {
        try {
            Vehicule vehicle = vehicleService.getVehiculeByVin(vin);
            return Response.ok(vehicle).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ── CREATE VEHICLE ───────────────────────────────────────
    @POST
    public Response createVehicle(Vehicule vehicle) {
        try {
            Vehicule created = vehicleService.addVehicule(vehicle);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ── UPDATE VEHICLE ───────────────────────────────────────
    @PUT
    @Path("/{vin}")
    public Response updateVehicule(@PathParam("vin") String vin, Vehicule updated) {
        try {
            Vehicule result = vehicleService.updateVehicule(vin, updated);
            return Response.ok(result).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ── DELETE VEHICLE ───────────────────────────────────────
    @DELETE
    @Path("/{vin}")
    public Response deleteVehicule(@PathParam("vin") String vin) {
        try {
            vehicleService.deleteVehicule(vin);
            return Response.ok("{\"message\": \"Véhicule supprimé avec succès.\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
