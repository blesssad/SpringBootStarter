package com.openschool.bookapplication.controller;

import com.openschool.bookapplication.entity.Book;
import com.openschool.bookapplication.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping("/one")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/many")
    public ResponseEntity<String> addBooks(@RequestBody List<Book> books) {
        bookService.addBooks(books);
        return new ResponseEntity<>("Books added successfully", HttpStatus.CREATED);

    }

    @GetMapping("/{name}")
    public ResponseEntity<Book> getBookByName(@PathVariable String name) {
        Book book = bookService.getBookByName(name);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable String category) {
        List<Book> books = bookService.getBooksByCategory(category);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateBook(@PathVariable String name, @RequestBody Book updatedBook) {
        bookService.updateBook(name, updatedBook);
        return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);

    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> removeBook(@PathVariable String name) {
        bookService.removeBook(name);
        return new ResponseEntity<>("Book removed successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}