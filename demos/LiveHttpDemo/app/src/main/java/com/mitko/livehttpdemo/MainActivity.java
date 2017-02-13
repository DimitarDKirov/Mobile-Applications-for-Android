package com.mitko.livehttpdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient httpClient;

    @Override
    protected void onStart() {
        final Context ctx=this;
        new PerformHttpAsyncTask(new OnPostExecuteFinished(){
            @Override
            public void call(String result) {
                Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
            }
        })
        .doInBackground("http://192.168.0.104:3001/api/books");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.httpClient = new OkHttpClient();
    }

    interface OnPostExecuteFinished{
        void call(String json);
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

            try {
                Response response=this.httpClient.newCall(request).execute();
                return  response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return  "";
            }
        }

        @Override
        protected void onPostExecute(String res) {
            //Update UI
            this.onPostFinished.call(res);
            super.onPostExecute(res);
        }
    }
}
