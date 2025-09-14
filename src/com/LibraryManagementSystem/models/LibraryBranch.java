package com.LibraryManagementSystem.models;

import java.util.*;
public class LibraryBranch {
    private String id;
    private String name;
    private String address;
    private List<String> bookInventory;

    public LibraryBranch(String name , String address){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.bookInventory = new ArrayList<>();
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

    public String getAddress(){
        return address;
    }
    public String setAddress(String address){
        return this.address = address;
    }

    public Set<String> getBookInventory() {
        return new HashSet<>(bookInventory);
    }
    public void addBook(String isbn) {
        bookInventory.add(isbn);
    }
    public void removeBook(String isbn) {
        bookInventory.remove(isbn);
    }

    public String toString() {
        return String.format("Branch{name='%s', address='%s', books=%d}", name, address, bookInventory.size());
    }
}
