package com.LibraryManagementSystem.services;
import com.LibraryManagementSystem.models.*;
import com.LibraryManagementSystem.models.enums.BookStatus;
import com.LibraryManagementSystem.repository.*;
import java.util.*;

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public ReservationService(ReservationRepository reservationRepository , BookRepository bookRepository , PatronRepository patronRepository){
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public boolean reserveBook(String isbn , String patronId){
        Optional<Book> bookOpt = bookRepository.findByIsbn(isbn);
        Optional<Patron> patronOpt = patronRepository.findById(patronId);

        if(bookOpt.isEmpty() || patronOpt.isEmpty()){
            return false;
        }

        Book book = bookOpt.get();

        if(book.getStatus() != BookStatus.CHECKED_OUT){
            return false;
        }

        List<Reservation> existingReservations = reservationRepository.findByPatronId(patronId);
        boolean alreadyReserved = existingReservations.stream()
                .anyMatch(r -> r.getIsbn().equals(isbn) && r.isActive());

        if(alreadyReserved){
            return false;
        }

        Reservation reservation = new Reservation(patronId,isbn);
        reservationRepository.save(reservation);
        return true;
    }

    public List<Reservation> getReservationsForPatron(String patronId){
        return reservationRepository.findByPatronId(patronId);
    }

    public List<Reservation> getReservationsForBook(String isbn){
        return reservationRepository.findActiveReservationsByIsbn(isbn);
    }
}
