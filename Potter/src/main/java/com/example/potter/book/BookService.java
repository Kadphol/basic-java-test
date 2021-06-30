package com.example.potter.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

    public Book addBook(Book book) {
        return repository.save(book);
    }

    public double orderBook(List<BookRequest> order) {
        List<Integer> buyList = new ArrayList<>();
        for (BookRequest request : order) {
            int id = request.getId();
            int count = request.getCount();
            Book volume = repository.getById(id);
            int stock = volume.getStock();
            if(stock < count) throw new ArithmeticException("Stock is less than order amount.");
            volume.setStock(stock-count);
            repository.save(volume);
            for(int i = 0; i < count; i++) {
                buyList.add(id);
            }
        }
        return calculatePrice(buyList);
    }

    double calculatePrice(List<Integer> buyList) {
        double price = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer id : buyList) {
            if(map.get(id) == null) map.put(id, 1);
            else map.put(id, map.get(id)+1);
        }
        int group = 0;
        int size = map.size();
        while(size > 0) {
            for (Map.Entry<Integer,Integer> order : map.entrySet()) {
                group = map.size();
                if(order.getValue() > 0) {
                    map.put(order.getKey(), order.getValue() - 1);
                }
            }
            map.keySet().removeIf(key -> map.get(key)==0);
            size = map.size();
            price += discount(group);
        }
        return price;
    }

    double discount(int group) {
        if(group == 5) return 5*0.75*100;
        if(group == 4) return 4*0.8*100;
        if(group == 3) return 3*0.9*100;
        if(group == 2) return 2*0.95*100;
        if(group == 1) return 100;
        return 0;
    }
}
