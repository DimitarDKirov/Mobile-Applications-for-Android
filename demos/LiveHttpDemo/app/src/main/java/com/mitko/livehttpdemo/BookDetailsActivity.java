package com.mitko.livehttpdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mitko.livehttpdemo.models.Book;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent intent=this.getIntent();
        Book book=(Book)intent.getSerializableExtra("book");

        final TextView tvTitle=(TextView) this.findViewById(R.id.tv_title);
        new PerformHttpAsyncTask(new OnPostExecuteFinished<Book>() {
            @Override
            public void call(Book book) {
                tvTitle.setText(book.title);
            }
        }).execute("http://192.168.0.104:3001/api/books/"+book.id);
    }

    class PerformHttpAsyncTask extends AsyncTask<String,String,String> {
        private final OkHttpClient httpClient;
        private final OnPostExecuteFinished onPostFinished;

        public PerformHttpAsyncTask(OnPostExecuteFinished onPostFinished) {
            this.httpClient = new OkHttpClient();
            this.onPostFinished = onPostFinished;
        }

        @Override
        protected String doInBackground(String... params) {
            //Do the HTTP request
            String url = params[0];
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            //синхронен request, самия метод се изпълнява асинхронно
            try {
                Response response = this.httpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String json) {
            //Update UI
            super.onPostExecute(json);

            Gson gson = new Gson();
            Book book = gson.fromJson(json, Book.class);

            this.onPostFinished.call(book);

        }
    }

    interface OnPostExecuteFinished<T> {
        void call(T json);
    }
}
