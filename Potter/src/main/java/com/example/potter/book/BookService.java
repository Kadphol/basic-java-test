package com.example.potter.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public BookResponse getBook(int id) throws BookNotFoundException {
        Optional<Book> result = repository.findById(id);
        if(result.isPresent()) {
            Book book = result.get();
            return new BookResponse(book.getId(), book.getTitle(), book.getPrice(), book.getStock());
        }
        throw new BookNotFoundException("Book id " + id + " NOT FOUND!");
    }

    public void setRepository(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookResponse> getAllBook() throws BookNotFoundException {
        List<Book> results = repository.findAll();
        List<BookResponse> responses = new ArrayList<>();
        if(results != null) {
            for (Book result : results) {
                responses.add(new BookResponse(result.getId(), result.getTitle(), result.getPrice(), result.getStock()));
            }
            return responses;
        }
        throw new BookNotFoundException("NO BOOKS FOUND!!");
    }
}
