package com.LibraryManagementSystem.services;

import com.LibraryManagementSystem.models.*;
import com.LibraryManagementSystem.observer.NotificationService;
import com.LibraryManagementSystem.observer.Observer;
import com.LibraryManagementSystem.utils.Logger;
import java.util.*;

public class LibraryService implements Observer {
    private final BookService bookService;
    private final PatronService patronService;
    private final LendingService lendingService;
    private final ReservationService reservationService;
    private final RecommendationService recommendationService;
    private final NotificationService notificationService;

    public LibraryService(BookService bookService, PatronService patronService,
                          LendingService lendingService, ReservationService reservationService,
                          RecommendationService recommendationService) {
        this.bookService = bookService;
        this.patronService = patronService;
        this.lendingService = lendingService;
        this.reservationService = reservationService;
        this.recommendationService = recommendationService;
        this.notificationService = new NotificationService();


        notificationService.addObserver(this);
        lendingService.setNotificationService(notificationService);
    }

    public void addBook(Book book) {
        bookService.addBook(book);
        Logger.info("Book added: " + book.getTitle());
    }

    public void removeBook(String isbn) {
        bookService.removeBook(isbn);
        Logger.info("Book removed: " + isbn);
    }

    public List<Book> searchBooks(String query) {
        return bookService.searchBooks(query);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public void addPatron(Patron patron) {
        patronService.addPatron(patron);
        Logger.info("Patron registered: " + patron.getName());
    }

    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    public boolean checkoutBook(String isbn, String patronId) {
        boolean success = lendingService.checkoutBook(isbn, patronId);
        if (success) {
            Logger.info("Book checked out - ISBN: " + isbn + ", Patron: " + patronId);
        }
        return success;
    }

    public boolean returnBook(String isbn, String patronId) {
        boolean success = lendingService.returnBook(isbn, patronId);
        if (success) {
            Logger.info("Book returned - ISBN: " + isbn + ", Patron: " + patronId);
        }
        return success;
    }

    // Reservation operations
    public boolean reserveBook(String isbn, String patronId) {
        boolean success = reservationService.reserveBook(isbn, patronId);
        if (success) {
            Logger.info("Book reserved - ISBN: " + isbn + ", Patron: " + patronId);
        }
        return success;
    }

    // Recommendation operations
    public List<Book> getRecommendations(String patronId) {
        return recommendationService.getRecommendations(patronId);
    }

    @Override
    public void update(String message) {
        Logger.info("Notification: " + message);
        System.out.println("📢 " + message);
    }
}
