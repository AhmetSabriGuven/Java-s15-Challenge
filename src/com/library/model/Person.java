package com.library.model;

public abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public abstract String whoYouAre();

    public String getName() {
        return name;
    }
}
