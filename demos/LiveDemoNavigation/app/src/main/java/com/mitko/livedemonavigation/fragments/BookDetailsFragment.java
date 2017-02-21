package com.mitko.livedemonavigation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mitko.livedemonavigation.R;
import com.mitko.livedemonavigation.models.Book;

import java.io.Serializable;

import static com.mitko.livedemonavigation.fragments.ListBooksFragment.BOOK_KEY;

public class BookDetailsFragment extends Fragment {
    private Book book;

    public BookDetailsFragment() {
        // Required empty public constructor
    }

    public static BookDetailsFragment newInstance(Book book) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BOOK_KEY, book);

        BookDetailsFragment fragment = new BookDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_book_details, container, false);

        //вариант с динамично създден fragment
        Bundle arguments = this.getArguments();
        Serializable book = null;
        if (arguments != null) {
            book = arguments.getSerializable(BOOK_KEY);
        }

        if (book != null) {
            this.setBook((Book) book, root);
        }

        return root;
    }

    //вариант вграден fragment
    public void setBook(Book book) {
        this.setBook(book, this.getView());
    }

    protected void setBook(Book book, View view) {
        this.book = book;
        TextView titleView = (TextView) view.findViewById(R.id.book_title);
        titleView.setText(book.getTitle());

        TextView isbnView = (TextView) view.findViewById(R.id.book_isbn);
        isbnView.setText(book.getIsbn());
    }
}
