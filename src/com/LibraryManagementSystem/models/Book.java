package com.LibraryManagementSystem.models;

import com.LibraryManagementSystem.models.enums.BookStatus;
import java.time.LocalDate;
import java.util.*;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private BookStatus status;
    private String currentBorrower;
    private LocalDate dueDate;
    private String branchLocation;

    public Book(String title , String author , String isbn , int publicationYear){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.status = BookStatus.AVAILABLE;
        this.branchLocation="MAIN_BRANCH";
    }

    public String getTitle(){
        return title;
    }
    public String setTitle(String title){
        return this.title = title;
    }

    public String getAuthor(){
        return author;
    }
    public String setAuthor(String author){
        return this.author = author;
    }


    public String getIsbn(){
        return isbn;
    }
    public String setIsbn(String isbn){
        return this.isbn = isbn;
    }


    public int getPublicationYear(){
        return publicationYear;
    }
    public int setPublicationYear(int publicationYear){
        return this.publicationYear = publicationYear;
    }


    public BookStatus getStatus(){
        return status;
    }
    public BookStatus setStatus(BookStatus status){
        return this.status = status;
    }


    public String getCurrentBorrower(){
        return currentBorrower;
    }
    public String setCurrentBorrower(String currentBorrower){
        return this.currentBorrower = currentBorrower;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }
    public LocalDate setDueDate(LocalDate dueDate){
        return this.dueDate = dueDate;
    }

    public String getBranchLocation(){
        return branchLocation;
    }
    public String setBranchLocation(String branchLocation) {
        return this.branchLocation = branchLocation;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj==null || getClass() != obj.getClass()){
            return false;
        }
        Book book = (Book) obj;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return String.format("Book{title='%s', author='%s', isbn='%s', year=%d, status=%s}",
                title, author, isbn, publicationYear, status);
    }
}
