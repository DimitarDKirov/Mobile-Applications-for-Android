package com.mitko.livedemonavigation.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mitko.livedemonavigation.R;
import com.mitko.livedemonavigation.models.Book;

public class BookDetailsActivity extends AppCompatActivity {
    public static final String  BOOK_KEY= "book_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent=this.getIntent();
        Book book=(Book)intent.getSerializableExtra(BOOK_KEY);

        TextView titleView=(TextView)this.findViewById(R.id.book_title);
        titleView.setText(book.getTitle());

        TextView isbnView=(TextView)this.findViewById(R.id.book_isbn);
        isbnView.setText(book.getIsbn());
    }
}
