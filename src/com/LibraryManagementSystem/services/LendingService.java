package com.LibraryManagementSystem.services;

import com.LibraryManagementSystem.models.*;
import com.LibraryManagementSystem.models.enums.BookStatus;
import com.LibraryManagementSystem.repository.*;
import com.LibraryManagementSystem.observer.NotificationService;
import java.time.LocalDate;
import java.util.*;

public class LendingService {
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final ReservationRepository reservationRepository;
    private NotificationService notificationService;

    public LendingService(BookRepository bookRepository , PatronRepository patronRepository , ReservationRepository reservationRepository){
        this.bookRepository=bookRepository;
        this.patronRepository=patronRepository;
        this.reservationRepository=reservationRepository;
    }

    public void setNotificationService(NotificationService notficationService){
        this.notificationService=notficationService;
    }

    public boolean checkoutBook(String isbn , String patronId) {
        Optional<Book> bookOpt = bookRepository.findByIsbn(isbn);
        Optional<Patron> patronOpt = patronRepository.findById(patronId);

        if (bookOpt.isEmpty() || patronOpt.isEmpty()) {
            return false;
        }

        Book book = bookOpt.get();
        Patron patron = patronOpt.get();

        if (book.getStatus() != BookStatus.AVAILABLE) {
            return false;
        }

        book.setStatus(BookStatus.CHECKED_OUT);
        book.setCurrentBorrower(patronId);
        book.setDueDate(LocalDate.now().plusDays(14));
        bookRepository.save(book);

        patron.borrowBook(isbn);
        patron.addBorrowedBook(isbn);
        patronRepository.save(patron);

        return true;
    }

    public boolean returnBook(String isbn , String patronId){
        Optional<Book> bookOpt = bookRepository.findByIsbn(isbn);
        Optional<Patron> patronOpt = patronRepository.findById(patronId);

        if (bookOpt.isEmpty() || patronOpt.isEmpty()) {
            return false;
        }

        Book book = bookOpt.get();
        Patron patron = patronOpt.get();

        if(!patronId.equals(book.getCurrentBorrower())){
            return false;
        }

        book.setStatus(BookStatus.AVAILABLE);
        book.setCurrentBorrower(null);
        book.setDueDate(null);
        bookRepository.save(book);

        patron.returnBook(isbn);
        patronRepository.save(patron);

        notifyReservationHolders(isbn);

        return true;
    }

    private void notifyReservationHolders(String isbn){
        if(notificationService != null){
            var reservations = reservationRepository.findActiveReservationsByIsbn(isbn);
            if(!reservations.isEmpty()){
                Reservation nextReservation = reservations.get(0);

                Optional<Patron> patronOpt = patronRepository.findById(nextReservation.getPatronId());
                Optional<Book> bookOpt = bookRepository.findByIsbn(isbn);

                if(patronOpt.isPresent() && bookOpt.isPresent()){
                    String message = String.format("Book '%s' is now available for %s",
                            bookOpt.get().getTitle(), patronOpt.get().getName());
                    notificationService.notifyObservers(message);
                }
            }
        }
}
