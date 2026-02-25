package com.location.controller;

import com.location.model.Agency;
import com.location.model.User;
import com.location.service.ManagerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Admin endpoints for Manager CRUD and Agency Assignment.
 * All routes under /api/admin/managers — protected by AuthFilter (role: Administrateur).
 */
@Path("/admin/managers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminManagerServlet {

    private final ManagerService managerService = new ManagerService();

    // ── GET ALL MANAGERS ─────────────────────────────────────
    @GET
    public Response getAllManagers() {
        List<User> managers = managerService.getAllManagers();
        return Response.ok(managers).build();
    }

    // ── GET MANAGER BY EMAIL ─────────────────────────────────
    @GET
    @Path("/{email}")
    public Response getManager(@PathParam("email") String email) {
        try {
            User manager = managerService.getManagerByEmail(email);
            return Response.ok(manager).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ── CREATE MANAGER ───────────────────────────────────────
    @POST
    public Response createManager(User manager) {
        try {
            if (manager.getEmail() == null || manager.getPassword() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Email et mot de passe sont obligatoires.\"}")
                        .build();
            }
            User created = managerService.createManager(manager);
            // Don't expose password in response
            created.setPassword(null);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ── UPDATE MANAGER ───────────────────────────────────────
    @PUT
    @Path("/{email}")
    public Response updateManager(@PathParam("email") String email, User updated) {
        try {
            User result = managerService.updateManager(email, updated);
            result.setPassword(null);
            return Response.ok(result).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ── DELETE MANAGER ───────────────────────────────────────
    @DELETE
    @Path("/{email}")
    public Response deleteManager(@PathParam("email") String email) {
        try {
            managerService.deleteManager(email);
            return Response.ok("{\"message\": \"Manager supprimé avec succès.\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ── ASSIGN MANAGER TO AGENCY ─────────────────────────────
    // POST /api/admin/managers/{email}/assign?agencyId=AGY-001
    @POST
    @Path("/{email}/assign")
    public Response assignToAgency(@PathParam("email") String email,
                                   @QueryParam("agencyId") String agencyId) {
        try {
            if (agencyId == null || agencyId.isBlank()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Le paramètre 'agencyId' est obligatoire.\"}")
                        .build();
            }
            Agency agency = managerService.assignManagerToAgency(email, agencyId);
            return Response.ok(agency).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ── UNASSIGN MANAGER FROM AGENCY ─────────────────────────
    @DELETE
    @Path("/{email}/assign")
    public Response unassignFromAgency(@PathParam("email") String email) {
        try {
            managerService.unassignManager(email);
            return Response.ok("{\"message\": \"Manager désassigné de l'agence.\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
