package com.example.potter.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;

    @Test
    public void foundBookID01() throws BookNotFoundException {
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

    @Test
    public void BookNotFound() {
        when(repository.findById(100)).thenReturn(
                Optional.empty()
        );

        BookService service = new BookService();
        service.setRepository(repository);

        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> service.getBook(100));
        assertEquals("Book id 100 NOT FOUND!",exception.getMessage());
    }

    @Test
    public void discountTest() {
        BookService service = new BookService();
        assertEquals(0, service.discount(0));
        assertEquals(100, service.discount(1));
        assertEquals(190, service.discount(2));
        assertEquals(270, service.discount(3));
        assertEquals(320, service.discount(4));
        assertEquals(375, service.discount(5));
    }

    @Test
    public void testBasicPrice() {
        BookService service = new BookService();
        assertEquals(100, service.calculatePrice(Arrays.asList(1)));
        assertEquals(100*2, service.calculatePrice(Arrays.asList(1,1)));
        assertEquals(100*3, service.calculatePrice(Arrays.asList(1,1,1)));
    }

    @Test
    public void testBasicDiscountPrice() {
        BookService service = new BookService();
        assertEquals(100*2*0.95, service.calculatePrice(Arrays.asList(1,2)));
        assertEquals(100*3*0.9, service.calculatePrice(Arrays.asList(1,2,3)));
        assertEquals(100*4*0.8, service.calculatePrice(Arrays.asList(1,2,3,4)));
        assertEquals(100*5*0.75, service.calculatePrice(Arrays.asList(1,2,3,4,5)));
    }
}