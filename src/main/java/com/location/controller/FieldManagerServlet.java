package com.location.controller;

import com.location.model.Vehicule;
import com.location.service.VehiculeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/field/vehicules")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FieldManagerServlet {

    private final VehiculeService vehiculeService = new VehiculeService();

    @GET
    public Response getAllVehicules() {
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        return Response.ok(vehicules).build();
    }
}