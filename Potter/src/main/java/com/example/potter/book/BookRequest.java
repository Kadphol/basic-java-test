package com.example.potter.book;

public class BookRequest {
    private int id;
    private int count;

    public BookRequest() { }

    public BookRequest(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }
}
