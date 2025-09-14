package com.LibraryManagementSystem.stratergy;

import com.LibraryManagementSystem.models.Book;
import com.LibraryManagementSystem.repository.BookRepository;
import java.util.*;

public class AuthorSearchStratergy {
    private final BookRepository bookRepository;

    public AuthorSearchStratergy(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> search(String query){
        return bookRepository.findByAuthor(query);
    }
}
