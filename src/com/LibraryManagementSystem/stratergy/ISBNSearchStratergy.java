package com.LibraryManagementSystem.stratergy;

import com.LibraryManagementSystem.models.Book;
import com.LibraryManagementSystem.repository.BookRepository;
import java.util.*;

public class ISBNSearchStratergy implements SearchStratergy {

    private final BookRepository bookRepository;

    public ISBNSearchStratergy(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> search(String query){
        Optional<Book> book = bookRepository.findByIsbn(query);
        return book.map(Collections::singletonList).orElseGet(Collections::emptyList);
    }
}
