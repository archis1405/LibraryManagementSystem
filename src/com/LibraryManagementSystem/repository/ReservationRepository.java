package com.LibraryManagementSystem.repository;

import com.LibraryManagementSystem.models.Reservation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ReservationRepository {
    private final Map<String,Reservation> reservations = new ConcurrentHashMap<>();

    public void save(Reservation reservation){
        reservations.put(reservation.getId(),reservation);
    }
    public Optional<Reservation> findById(String id){
        return Optional.ofNullable(reservations.get(id));
    }
    public List<Reservation> findAll(){
        return new ArrayList<>(reservations.values());
    }

    public List<Reservation> findActiveReservationsByIsbn(String isbn){
        return reservations.values().stream().filter(r -> r.getIsbn().equals(isbn) && r.isActive())
                .sorted(Comparator.comparing(Reservation::getReservationDate))
                .collect(Collectors.toList());
    }

    public List<Reservation> findByPatronId(String patronId){
        return reservations.values().stream()
                .filter(r->r.getPatronId().equals(patronId))
                .collect(Collectors.toList());
    }
}
