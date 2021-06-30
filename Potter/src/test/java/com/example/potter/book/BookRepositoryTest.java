package com.example.potter.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void testGetByID() {
        repository.save(new Book(1,"Harry Potter and Philosopher's Stone",100,100));

        Book book = repository.getById(1);
        assertEquals(1, book.getId());
        assertEquals("Harry Potter and Philosopher's Stone", book.getTitle());
        assertEquals(100, book.getPrice());
        assertEquals(100, book.getStock());
    }
}