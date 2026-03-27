package com.library.model;

import java.time.LocalDate;

public class Journals extends Book {
    public Journals(Long id, String name, String author, double price, String edition, LocalDate date) {
        super(id, name, author, price, edition, date);
    }

    @Override
    public String getType() {
        return "Journal";
    }
}