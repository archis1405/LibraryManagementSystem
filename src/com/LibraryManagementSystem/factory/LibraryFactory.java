package com.LibraryManagementSystem.factory;

import com.LibraryManagementSystem.repository.*;
import com.LibraryManagementSystem.services.*;


public class LibraryFactory {

    public static LibraryService createLibraryService() {

        BookRepository bookRepository = new BookRepository();
        PatronRepository patronRepository = new PatronRepository();
        BranchRepository branchRepository = new BranchRepository();
        ReservationRepository reservationRepository = new ReservationRepository();


        BookService bookService = new BookService(bookRepository);
        PatronService patronService = new PatronService(patronRepository);
        LendingService lendingService = new LendingService(bookRepository, patronRepository, reservationRepository);
        ReservationService reservationService = new ReservationService(reservationRepository, bookRepository, patronRepository);
        RecommendationService recommendationService = new RecommendationService(bookRepository, patronRepository);

        return new LibraryService(bookService, patronService, lendingService, reservationService, recommendationService);
    }

    public static BookService createBookService() {
        return new BookService(new BookRepository());
    }

    public static PatronService createPatronService() {
        return new PatronService(new PatronRepository());
    }

}
