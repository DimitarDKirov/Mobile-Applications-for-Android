package com.mitko.livehttpdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mitko.livehttpdemo.models.Book;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent intent=this.getIntent();
        Book book=(Book)intent.getSerializableExtra("book");

        ((TextView)this.findViewById(R.id.tv_title)).setText(book.title);
    }
}
