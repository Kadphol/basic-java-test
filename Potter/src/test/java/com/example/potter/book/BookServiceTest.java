package com.example.potter.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;

    @Test
    public void foundBookID01() {
        Book mock = new Book(1,"Harry Potter and Philosopher's Stone",100,100);
        when(repository.findById(1)).thenReturn(
                Optional.of(mock)
        );

        BookService service = new BookService();
        service.setRepository(repository);

        BookResponse result = service.getBook(1);
        assertEquals(1, result.getId());
        assertEquals("Harry Potter and Philosopher's Stone", result.getTitle());
        assertEquals(100, result.getPrice());
        assertEquals(100, result.getStock());
    }
}