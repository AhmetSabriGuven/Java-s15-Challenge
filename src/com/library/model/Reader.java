package com.library.model;

import java.util.HashSet;
import java.util.Set;

public class Reader extends Person {

    private Set<Book> books = new HashSet<>();

    public Reader(String name) {
        super(name);
    }

    public void purchaseBook(Book book) {
        books.add(book);
        book.changeOwner(this);
        book.updateStatus(false);
    }

    public void borrowBook(Book book) {
        books.add(book);
        book.changeOwner(this);
    }

    public void returnBook(Book book) {
        books.remove(book);
        book.changeOwner(null);
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("Okuyucuda kitap yok.");
            return;
        }

        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public String whoYouAre() {
        return "Reader: " + getName();
    }
}