package com.mitko.livedemonavigation.models;

import java.io.Serializable;

/**
 * Created by dimki on 07.02.2017 г..
 */

public class Book implements Serializable{
    private String isbn;
    private  String title;

    public Book(String isbn, String title) {
        this.setIsbn(isbn);
        this.setTitle(title);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
