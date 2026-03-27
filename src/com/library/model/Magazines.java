package com.library.model;

import java.time.LocalDate;

public class Magazines extends Book {
    public Magazines(Long id, String name, String author, double price, String edition, LocalDate date) {
        super(id, name, author, price, edition, date);
    }

    @Override
    public String getType() {
        return "Magazine";
    }
}