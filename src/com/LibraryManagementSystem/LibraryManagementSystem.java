package com.LibraryManagementSystem;

import com.LibraryManagementSystem.models.*;
import com.LibraryManagementSystem.services.*;
import com.LibraryManagementSystem.factory.LibraryFactory;
import com.LibraryManagementSystem.utils.Logger;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Logger.info("Starting Library Management System");


        LibraryService library = LibraryFactory.createLibraryService();


        demonstrateLibrarySystem(library);

        Logger.info("Library Management System demonstration completed");
    }

    private static void demonstrateLibrarySystem(LibraryService library) {

        Book book1 = new Book("Effective Java", "Joshua Bloch", "978-0134685991", 2017);
        Book book2 = new Book("Clean Code", "Robert C. Martin", "978-0132350884", 2008);
        Book book3 = new Book("Design Patterns", "Gang of Four", "978-0201633612", 1994);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);


        Patron patron1 = new Patron("Alice Johnson", "alice@email.com", "123 Oak Street");
        Patron patron2 = new Patron("Bob Smith", "bob@email.com", "456 Pine Avenue");

        library.addPatron(patron1);
        library.addPatron(patron2);


        library.checkoutBook(book1.getIsbn(), patron1.getId());
        library.checkoutBook(book2.getIsbn(), patron1.getId());


        System.out.println("\n--- Search Results for 'Java' ---");
        library.searchBooks("Java").forEach(System.out::println);


        library.returnBook(book1.getIsbn(), patron1.getId());


        library.checkoutBook(book1.getIsbn(), patron2.getId());
        library.reserveBook(book1.getIsbn(), patron1.getId());


        library.returnBook(book1.getIsbn(), patron2.getId());


        System.out.println("\n--- Recommendations for " + patron1.getName() + " ---");
        library.getRecommendations(patron1.getId()).forEach(System.out::println);


        System.out.println("\n--- Current Inventory ---");
        library.getAllBooks().forEach(book ->
                System.out.println(book.getTitle() + " - Status: " + book.getStatus()));
    }
}
