package com.mitko.livehttpdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mitko.livehttpdemo.models.Book;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private Book[] books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        ListView lv_books=(ListView) this.findViewById(R.id.lv_books);
        lv_books.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book=books[position];
                Intent intent=new Intent(parent.getContext(),BookDetailsActivity.class);
                intent.putExtra("book", book);
                parent.getContext().startActivity(intent);
            }
        });
        lv_books.setAdapter(adapter);

        final MainActivity activity =this;
        Button button=(Button)this.findViewById(R.id.btn_http);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onCLick(v);
            }
        });
    }

    private void onCLick(View view) {
        final MainActivity activity=this;
        final ProgressDialog mDialog=new ProgressDialog(this);
        mDialog.setMessage("Loading... Please wait");
        mDialog.setCancelable(false);
        mDialog.show();
        new PerformHttpAsyncTask(new OnPostExecuteFinished<Book>(){

            @Override
            public void call(Book[] books) {
            //нужен е при async HTTP request
//               activity.runOnUiThread(new Runnable() {
//                   @Override
//                   public void run() {
                activity.books=books;
                for (Book book : books) {
                    activity.adapter.add(book.title);
                }
                mDialog.cancel();
                   }
//               });
//            }
        }).execute("http://192.168.0.104:3001/api/books");
    }

    interface OnPostExecuteFinished<T>{
        void call(T[] json);
    }

    class PerformHttpAsyncTask extends AsyncTask<String,String,String>{
        private final OkHttpClient httpClient;
        private final OnPostExecuteFinished onPostFinished;

        public PerformHttpAsyncTask(OnPostExecuteFinished onPostFinished) {
            this.httpClient=new OkHttpClient();
            this.onPostFinished=onPostFinished;
        }

        @Override
        protected String doInBackground(String... params) {
         //Do the HTTP request
            String url=params[0];
            Request request=new Request.Builder()
                    .url(url)
                    .build();

          //синхронен request, самия метод се изпълнява асинхронно
            try {
                Response response=this.httpClient.newCall(request).execute();
                return  response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return  "";
            }
         //асинхронен request
//            this.httpClient.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    String json=response.body().string();
//                    onPostFinished.call(json);
//                }
//            });
//            return  "";
        }

        @Override
        protected void onPostExecute(String json) {
            //Update UI
            super.onPostExecute(json);

            Gson gson=new Gson();
            Book[] books=gson.fromJson(json,Book[].class);

            this.onPostFinished.call(books);

        }
    }
}
