package com.location.controller;

import com.location.model.Reservation;
import com.location.service.ReservationService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationServlet {

    private final ReservationService reservationService = new ReservationService();

    // Create reservation
    @POST
    public Response createReservation(Reservation reservation) {
        try {
            Reservation created = reservationService.createReservation(reservation);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Get all reservations
    @GET
    public Response getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return Response.ok(reservations).build();
    }

    // Get reservation by ID
    @GET
    @Path("/{id}")
    public Response getReservationById(@PathParam("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return reservationService.getReservation(uuid)
                    .map(reservation -> Response.ok(reservation).build())
                    .orElse(Response.status(Response.Status.NOT_FOUND)
                            .entity("{\"error\": \"Reservation not found\"}")
                            .build());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Invalid UUID format\"}")
                    .build();
        }
    }

    // Confirm reservation
    @PUT
    @Path("/{id}/confirm")
    public Response confirmReservation(@PathParam("id") String id) {
        try {
            reservationService.confirmReservation(UUID.fromString(id));
            return Response.ok("{\"message\": \"Reservation confirmed\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Cancel reservation
    @PUT
    @Path("/{id}/cancel")
    public Response cancelReservation(@PathParam("id") String id) {
        try {
            reservationService.cancelReservation(UUID.fromString(id));
            return Response.ok("{\"message\": \"Reservation cancelled\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Delete reservation
    @DELETE
    @Path("/{id}")
    public Response deleteReservation(@PathParam("id") String id) {
        try {
            reservationService.deleteReservation(UUID.fromString(id));
            return Response.ok("{\"message\": \"Reservation deleted\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Invalid ID\"}")
                    .build();
        }
    }
}
