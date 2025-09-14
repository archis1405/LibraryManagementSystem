package com.LibraryManagementSystem.repository;

import com.LibraryManagementSystem.models.Patron;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PatronRepository {
    private final Map<String, Patron> patrons = new ConcurrentHashMap<>();

    public void save(Patron patron) {
        patrons.put(patron.getId(), patron);
    }

    public Optional<Patron> findById(String id) {
        return Optional.ofNullable(patrons.get(id));
    }

    public List<Patron> findAll() {
        return new ArrayList<>(patrons.values());
    }

    public Optional<Patron> findByEmail(String email) {
        return patrons.values().stream()
                .filter(patron -> patron.getEmail().equals(email))
                .findFirst();
    }

    public boolean delete(String id) {
        return patrons.remove(id) != null;
    }
}
