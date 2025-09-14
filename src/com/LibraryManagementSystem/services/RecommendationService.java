package com.LibraryManagementSystem.services;

import com.LibraryManagementSystem.models.*;
import com.LibraryManagementSystem.repository.*;
import java.util.*;
import java.util.stream.Collectors;

public class RecommendationService {
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public RecommendationService(BookRepository bookRepository, PatronRepository patronRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public List<Book> getRecommendations(String patronId){
        Optional<Patron> patronOpt = patronRepository.findById(patronId);

        if (patronOpt.isEmpty()) {
            return Collections.emptyList();
        }

        Patron patron = patronOpt.get();
        List<String> borrowingHistory = patron.getBorrowedBooks();

        if (borrowingHistory.isEmpty()) {
            return getPopularBooks();
        }

        Set<String> favoriteAuthors = borrowingHistory.stream()
                .map(isbn -> bookRepository.findByIsbn(isbn))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Book::getAuthor)
                .collect(Collectors.toSet());

        
        List<Book> recommendations = bookRepository.findAll().stream()
                .filter(book -> favoriteAuthors.contains(book.getAuthor()))
                .filter(book -> !borrowingHistory.contains(book.getIsbn()))
                .limit(5)
                .collect(Collectors.toList());

        return recommendations;
    }

    private List<Book> getPopularBooks() {

        return bookRepository.findAll().stream()
                .limit(5)
                .collect(Collectors.toList());
    }
}
