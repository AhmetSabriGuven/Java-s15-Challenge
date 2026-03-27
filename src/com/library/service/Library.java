package com.library.service;

import com.library.model.Book;
import com.library.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Library {

    private Map<Long, Book> books = new HashMap<>();
    private List<Reader> readers = new ArrayList<>();

    public Map<Long, Book> getBooks() {
        return books;
    }

    public List<Reader> getReader() {
        return readers;
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void newBook(Book book) {
        books.put(book.getBookId(), book);
    }

    public void lendBook(Long id, Reader reader) {
        Book book = books.get(id);

        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Kitap mevcut değil.");
            return;
        }

        book.updateStatus(false);
        book.changeOwner(reader);
        reader.borrowBook(book);

        System.out.println("Kitap verildi.");
    }

    public void takeBackBook(Long id, Reader reader) {
        Book book = books.get(id);

        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("Kitap zaten kütüphanede.");
            return;
        }

        book.updateStatus(true);
        book.changeOwner(null);
        reader.returnBook(book);

        System.out.println("Kitap geri alındı.");
    }

    public void showBook() {
        if (books.isEmpty()) {
            System.out.println("Kütüphanede kitap yok.");
            return;
        }

        for (Book book : books.values()) {
            book.display();
        }
    }
}