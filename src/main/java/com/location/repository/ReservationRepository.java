package com.location.repository;

import com.location.model.Reservation;

import java.util.*;

public class ReservationRepository {

    // Shared in-memory storage
    private static final Map<UUID, Reservation> reservations = new HashMap<>();

    // Save or update reservation
    public void save(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    // Find reservation by ID
    public Optional<Reservation> findById(UUID id) {
        return Optional.ofNullable(reservations.get(id));
    }

    // Get all reservations
    public List<Reservation> findAll() {
        return new ArrayList<>(reservations.values());
    }

    // Find reservations by client ID
    public List<Reservation> findByClientId(UUID clientId) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservations.values()) {
            if (reservation.getClientId().equals(clientId)) {
                result.add(reservation);
            }
        }
        return result;
    }

    // Delete reservation
    public void delete(UUID id) {
        reservations.remove(id);
    }

    // Check if reservation exists
    public boolean exists(UUID id) {
        return reservations.containsKey(id);
    }
}
