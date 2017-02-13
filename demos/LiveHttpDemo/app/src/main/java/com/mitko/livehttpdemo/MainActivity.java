package com.mitko.livehttpdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mitko.livehttpdemo.models.Book;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.httpClient = new OkHttpClient();
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
        new PerformHttpAsyncTask(new OnPostExecuteFinished<Book>(){
            @Override
            public void call(Book[] result) {
            //нужен е при async HTTP request
//               activity.runOnUiThread(new Runnable() {
//                   @Override
//                   public void run() {
                      // Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();
                   }
//               });
//            }
        }).execute("http://10.82.200.82:3001/api/books");
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
