package com.example.potter.book;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class BookService {

    @Autowired
    private BookRepository repository;

    public BookResponse getBook(int id) {
        Optional<Book> result = repository.findById(id);
        if(result.isPresent()) {
            Book book = result.get();
            return new BookResponse(book.getId(), book.getTitle(), book.getPrice(), book.getStock());
        }
        return new BookResponse();
    }
}
