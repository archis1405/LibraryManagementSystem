package com.LibraryManagementSystem.stratergy;

import com.LibraryManagementSystem.models.Book;
import com.LibraryManagementSystem.repository.BookRepository;
import java.util.*;

public class TitleSearchStratergy implements SearchStratergy {
    private final BookRepository bookRepository;

    public TitleSearchStratergy(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> search(String query){
        return bookRepository.findByTitle(query);
    }
}
