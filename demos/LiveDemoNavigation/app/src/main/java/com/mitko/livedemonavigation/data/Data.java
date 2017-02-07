package com.mitko.livedemonavigation.data;

import com.mitko.livedemonavigation.models.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimki on 07.02.2017 г..
 */

public class Data {
private  static List<Book> books;

    static {
        books=new ArrayList<Book>();
        int count=100;

        for (int i=0;i<count;i++){
            String isbn="ISBN"+i;
            String title="Title"+(i+1);
            books.add(new Book(isbn,title));
        }
    }

    public List<Book> getBooks(){
        return new ArrayList<>(books);
    }

    public Book getBookByIsbn(String isbn){
        for (Book book:books){
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }

        return null;
    }

    public Book createBook(String isbn, String title){
        Book book =new Book(isbn, title);
        books.add(book);
        return book;
    }

}
