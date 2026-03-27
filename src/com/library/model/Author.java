package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private List<Book> books = new ArrayList<>();

    public Author(String name) {
        super(name);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void showBook() {
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public String whoYouAre() {
        return "Author: " + getName();
    }
}
