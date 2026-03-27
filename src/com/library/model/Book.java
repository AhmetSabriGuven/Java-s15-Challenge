package com.library.model;

import java.time.LocalDate;

public class Book {

    private Long bookId;
    private String author;
    private String name;
    private double price;
    private boolean status;
    private String edition;
    private LocalDate dateOfPurchase;
    private Reader owner;

    public Book(Long bookId, String name, String author, double price, String edition, LocalDate dateOfPurchase) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.price = price;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.status = true;
        this.owner = null;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Reader getOwner() {
        return owner;
    }

    public boolean isAvailable() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public String getEdition() {
        return edition;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void changeOwner(Reader owner) {
        this.owner = owner;
    }

    public String getType() {
        return "Book";
    }

    public void display() {
        System.out.println("ID: " + getBookId() +
                " | Tür: " + getType() +
                " | Ad: " + getTitle() +
                " | Yazar: " + getAuthor() +
                " | Fiyat: " + getPrice() +
                " | Baskı: " + getEdition() +
                " | Satın Alma Tarihi: " + getDateOfPurchase() +
                " | Durum: " + (isAvailable() ? "Müsait" : "Ödünçte"));
    }

    public void updateStatus(boolean status) {
        this.status = status;
    }
}