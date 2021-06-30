package com.example.potter.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books/{id}")
    public BookResponse getBookByID(@PathVariable String id) throws BookNotFoundException {
        int _id;
        try {
            _id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid id: " + id);
        }
        return bookService.getBook(_id);
    }

    @GetMapping("/books")
    public List<BookResponse> getAllBooks() throws BookNotFoundException {
        return bookService.getAllBook();
    }
}
