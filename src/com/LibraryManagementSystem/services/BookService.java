package com.LibraryManagementSystem.services;

import com.LibraryManagementSystem.models.Book;
import com.LibraryManagementSystem.repository.BookRepository;
import com.LibraryManagementSystem.stratergy.*;
import java.util.*;

public class BookService {
    private final BookRepository bookRepository;
    private final Map<String, SearchStratergy> searchStratergyMap;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
        this.searchStratergyMap=new HashMap<>();

        initializeSearchStratergies();
    }

    private void initializeSearchStratergies(){
        searchStratergyMap.put("title", new TitleSearchStratergy(bookRepository));
        searchStratergyMap.put("author", new AuthorSearchStratergy(bookRepository));
        searchStratergyMap.put("isbn", new ISBNSearchStratergy(bookRepository));
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void removeBook(String isbn){
        bookRepository.delete(isbn);
    }

    public Optional<Book> getBook(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> searchBooks(String query){
        Set<Book> result = new HashSet<>();

        for(SearchStratergy stratergy:searchStratergyMap.values()){
            result.addAll(stratergy.search(query));
        }

        return new ArrayList<>(result);
    }
}
