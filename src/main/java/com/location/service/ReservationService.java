package com.location.service;

import com.location.model.Reservation;
import com.location.model.enums.ReservationStatus;
import com.location.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationService {

    private final ReservationRepository reservationRepository = new ReservationRepository();

    // Create reservation
    public Reservation createReservation(Reservation reservation) {

        // Basic validation
        if (reservation.getDepartureDateTime().isAfter(reservation.getReturnDateTime())) {
            throw new IllegalArgumentException("Return date must be after departure date");
        }

        if (reservation.getDepartureDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Departure date cannot be in the past");
        }

        reservationRepository.save(reservation);
        return reservation;
    }

    // Get reservation by ID
    public Optional<Reservation> getReservation(UUID id) {
        return reservationRepository.findById(id);
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Get reservations by client
    public List<Reservation> getReservationsByClient(UUID clientId) {
        return reservationRepository.findByClientId(clientId);
    }

    // Confirm reservation
    public void confirmReservation(UUID id) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);

        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setStatus(ReservationStatus.CONFIRMED);
            reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Reservation not found");
        }
    }

    // Cancel reservation
    public void cancelReservation(UUID id) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);

        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();

            if (!reservation.isCancellable()) {
                throw new RuntimeException("Reservation cannot be cancelled");
            }

            reservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Reservation not found");
        }
    }

    // Delete reservation
    public void deleteReservation(UUID id) {
        reservationRepository.delete(id);
    }
}