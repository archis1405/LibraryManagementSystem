package com.LibraryManagementSystem.models;

import java.time.LocalDateTime;
import java.util.*;

public class Reservation {
    private String id;
    private String patronId;
    private String isbn;
    private LocalDateTime reservationDate;
    private boolean isActive;

    public Reservation(String patronId, String isbn) {
        this.id = UUID.randomUUID().toString();
        this.patronId = patronId;
        this.isbn = isbn;
        this.reservationDate = LocalDateTime.now();
        this.isActive = true;
    }

    public String getId() {
        return id;
    }

    public String getPatronId() {
        return patronId;
    }


    public String getIsbn() {
        return isbn;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return String.format("Reservation{patronId='%s', isbn='%s', date=%s}",
                patronId, isbn, reservationDate);
    }
}
