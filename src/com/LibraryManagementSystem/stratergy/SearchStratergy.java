package com.LibraryManagementSystem.stratergy;
import com.LibraryManagementSystem.models.Book;
import java.util.*;

public interface SearchStratergy {
    List<Book> search(String query);
}
