package com.LibraryManagementSystem.services;

import com.LibraryManagementSystem.models.Patron;
import com.LibraryManagementSystem.repository.PatronRepository;
import java.util.*;

public class PatronService {
    private final PatronRepository patronRepository;

    public PatronService(PatronRepository patronRepository){
        this.patronRepository=patronRepository;
    }

    public void addPatron(Patron patron){
        patronRepository.save(patron);
    }

    public Optional<Patron> getPatron(String id){
        return patronRepository.findById(id);
    }

    public List<Patron> getAllPatrons(){
        return patronRepository.findAll();
    }

    public void updatePatron(Patron patron){
        patronRepository.save(patron);
    }
}
