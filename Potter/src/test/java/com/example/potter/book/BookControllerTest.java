package com.example.potter.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository repository;

    @Test
    public void callApiWithPathVariable() {
        repository.save(new Book(1,"Harry Potter and Philosopher's Stone",100,100));

        BookResponse expected = new BookResponse(1,"Harry Potter and Philosopher's Stone",100,100);
        BookResponse response = restTemplate.getForObject("/books/1", BookResponse.class);
        assertEquals(1, response.getId());
        assertEquals("Harry Potter and Philosopher's Stone", response.getTitle());
        assertEquals(100, response.getPrice());
        assertEquals(100, response.getStock());
        assertEquals(expected, response);
    }

    @Test
    public void createNewBook() {
        Book request = new Book(3,"Harry Potter and Prisoner of Azkaban",100,100);
        BookResponse response = restTemplate.postForObject("/book", request, BookResponse.class);
        assertEquals(request.getId(), response.getId());
        assertEquals(request.getTitle(), response.getTitle());
        assertEquals(request.getPrice(), response.getPrice());
        assertEquals(request.getStock(), response.getStock());
    }


}