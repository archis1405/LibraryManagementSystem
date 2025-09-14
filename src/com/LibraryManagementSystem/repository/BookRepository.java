package com.LibraryManagementSystem.repository;

import com.LibraryManagementSystem.models.Book;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookRepository {
    private final Map<String, Book> books = new ConcurrentHashMap<>();

    public void save(Book book) {
        books.put(book.getIsbn(), book);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    public List<Book> findAll(){
        return new ArrayList<>(books.values());
    }

    public List<Book> findByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> findByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean delete(String isbn) {
        return books.remove(isbn) != null;
    }
    public long count() {
        return books.size();
    }
}
