package com.mitko.livedemonavigation.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mitko.livedemonavigation.R;
import com.mitko.livedemonavigation.fragments.BookDetailsFragment;
import com.mitko.livedemonavigation.fragments.DrawerFragment;
import com.mitko.livedemonavigation.models.Book;
import com.mitko.livedemonavigation.models.DrawerItemInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class BookDetailsActivity extends BaseDrawerActivity {
    public static final String  BOOK_KEY= "book_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent=this.getIntent();
        Book book=(Book)intent.getSerializableExtra(BOOK_KEY);

        //вариант вграден fragment
//        BookDetailsFragment fragment= (BookDetailsFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_book_details);
//        fragment.setBook(book);

        //вариант с динамично създден fragment
        Fragment fragment=BookDetailsFragment.newInstance(book);

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment, fragment)
                .commit();
    }
}
