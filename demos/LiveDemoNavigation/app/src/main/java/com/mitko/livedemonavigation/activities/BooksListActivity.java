package com.mitko.livedemonavigation.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mitko.livedemonavigation.R;
import com.mitko.livedemonavigation.data.Data;
import com.mitko.livedemonavigation.models.Book;

import java.util.List;

public class BooksListActivity extends AppCompatActivity {
    private static final String INTENT_EXTRA_KEY = "args_key";
    private final Data data;

    private int count;

    public BooksListActivity() {
        this(new Data());
    }

    public BooksListActivity(Data data) {
        super();
        this.data = data;
        this.count=1;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent=this.getIntent();
        this.count=intent.getIntExtra(INTENT_EXTRA_KEY, 1);
        TextView tvCounter = (TextView) this.findViewById(R.id.tv_counter);
        tvCounter.setText(String.format("Created %d Main Activity", count));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);

//        Intent intent=this.getIntent();
//        int count=intent.getIntExtra(INTENT_EXTRA_KEY, 1);
//
//        TextView tvCounter = (TextView) this.findViewById(R.id.tv_counter);
//        tvCounter.setText(String.format("Created %d Main Activity", count));

        Button btnGoNext = (Button) this.findViewById(R.id.button_go_next);
        btnGoNext.setOnClickListener(view -> {
            //Intent initialIntent = new Intent(this, BooksListActivity.class);
            Intent initialIntent = new Intent(this, BookDetailsActivity.class);
            initialIntent.putExtra(INTENT_EXTRA_KEY, this.count+1);
            this.startActivity(initialIntent);
        });

        ListView lvBooks=(ListView)this.findViewById(R.id.lv_books);
        List<Book> books= this.data.getBooks();

        ArrayAdapter<Book> booksAdapter=new ArrayAdapter<Book>(this, -1,books){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view=convertView;
                if(view==null){
                    LayoutInflater inflater=LayoutInflater.from(this.getContext());
                    view=inflater.inflate(R.layout.item_book, parent, false);
                }

                TextView tvTitle=(TextView) view.findViewById(R.id.tv_title);
                String title=this.getItem(position).getTitle();
                tvTitle.setText(title);
                return view;
            }
        };

        lvBooks.setAdapter(booksAdapter);

        lvBooks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent=new Intent(this, BookDetailsActivity.class);
            Book book=books.get(position);
            intent.putExtra(BookDetailsActivity.BOOK_KEY, book);
            this.startActivity(intent);
        });
    }
}
