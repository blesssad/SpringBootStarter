package com.openschool.bookapplication.service;

import com.openschool.bookapplication.entity.Book;
import com.openschool.bookapplication.entity.BookException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final Map<String, Book> books = new HashMap<>();

    public void addBook(Book book){
        books.put(book.getName(), book);
    }

    public void addBooks(List<Book> newBooks) {
        if (newBooks.size() == 1){
            throw new BookException("Используйте метод addBook(Book book)");
        }

        books.putAll(newBooks.stream().collect(Collectors.toMap(Book::getName, Function.identity())));
    }

    public Book getBookByName(String name){
        return books.get(name);
    }

    public List<Book> getBooksByCategory(String category){
        return books.values().stream().filter(book -> book.getCategory().equals(category)).collect(Collectors.toList());
    }

    public void updateBook(String name, Book updatedBook){
        if (!books.containsKey(name)) {
            throw new BookException("Книга с названием " + name + " не найдена.");
        }

        books.put(name, updatedBook);
    }

    public void removeBook(String name){
        if (!books.containsKey(name)) {
            throw new BookException("Книга с названием " + name + " не найдена.");
        }

        books.remove(name);
    }

    public List<Book> getAllBooks(){
        return new ArrayList<>(books.values());
    }
}
