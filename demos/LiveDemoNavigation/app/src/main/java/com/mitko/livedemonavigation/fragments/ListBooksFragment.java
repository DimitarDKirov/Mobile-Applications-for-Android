package com.mitko.livedemonavigation.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mitko.livedemonavigation.R;
import com.mitko.livedemonavigation.activities.BookDetailsActivity;
import com.mitko.livedemonavigation.activities.BooksListActivity;
import com.mitko.livedemonavigation.data.Data;
import com.mitko.livedemonavigation.models.Book;
import com.mitko.livedemonavigation.models.ICanNavigateActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListBooksFragment extends Fragment{
    public static final String  BOOK_KEY= "book_key";
    private Data data;

    public ListBooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_books, container, false);
        this.data = new Data();

        ListView lvBooks = (ListView) root.findViewById(R.id.lv_books);
        List<Book> books = this.data.getBooks();

        ArrayAdapter<Book> booksAdapter = new ArrayAdapter<Book>(root.getContext(), -1, books) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.item_book, parent, false);
                }

                TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                String title = this.getItem(position).getTitle();
                tvTitle.setText(title);
                return view;
            }
        };

        lvBooks.setAdapter(booksAdapter);

        lvBooks.setOnItemClickListener((parent, view, position, id) -> {

            //if activity != ICanNavigateActivity => Do Nothing
            ICanNavigateActivity<Book> activity=(ICanNavigateActivity<Book>) this.getActivity();
            activity.navigate(books.get(position));
        });

        // Inflate the layout for this fragment
        return root;
    }
}
