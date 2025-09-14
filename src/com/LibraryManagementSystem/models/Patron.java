package com.LibraryManagementSystem.models;

import java.util.*;
import java.time.LocalDate;

public class Patron {
    private String id;
    private String name;
    private String email;
    private String address;
    private LocalDate membershipDate;
    private List<String> borrowedBooks;
    private Set<String> currentBorrowedBooks;
    private List<String> preferences;

    public Patron(String name , String email , String address){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.email = email;
        this.membershipDate = LocalDate.now();
        this.borrowedBooks = new ArrayList<>();
        this.currentBorrowedBooks = new HashSet<>();
        this.preferences = new ArrayList<>();
    }

    public String getId(){
        return id;
    }


    public String getName(){
        return name;
    }
    public String setName(String name){
        return this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public String setEmail(String email){
        return this.email = email;
    }

    public String getAddress(){
        return address;
    }
    public String setAddress(String address){
        return this.address = address;
    }

    public LocalDate getMembershipDate(){
        return membershipDate;
    }

    public List<String> getBorrowedBooks(){
        return borrowedBooks;
    }
    public void addBorrowedBook(String isbn){
        borrowedBooks.add(isbn);
    }


    public Set<String> getCurrentBorrowedBooks(){
        return new HashSet<>(currentBorrowedBooks);
    }
    public void borrowBook(String isbn) {
        currentBorrowedBooks.add(isbn);
    }
    public void returnBook(String isbn) {
        currentBorrowedBooks.remove(isbn);
    }


    public List<String> getPreferences() {
        return new ArrayList<>(preferences);
    }
    public void addPreference(String preference) {
        preferences.add(preference);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Patron patron = (Patron) obj;
        return Objects.equals(id, patron.id);
    }


    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Patron{id='%s', name='%s', email='%s'}", id, name, email);
    }

}
