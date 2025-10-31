package com.br.jonas.basic_cache.controller;

import com.br.jonas.basic_cache.model.Book;
import com.br.jonas.basic_cache.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Book> getBook(@RequestParam String title) {
        Book book = bookService.getBookByTitle(title);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }
}
