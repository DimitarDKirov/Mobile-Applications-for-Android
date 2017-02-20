package mitko.liveworkshopui.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import mitko.liveworkshopui.models.Superhero;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dimki on 19.02.2017 г..
 */

public class HttpGetAsyncTask<T> extends HttpAsyncTask<T> {

    public HttpGetAsyncTask(Class<T> klass, OnDataReady<T> onDataReady) {
       super(klass,onDataReady);
    }

    @Override
    protected Request buildRequest(String url) {
    Request request=new Request.Builder()
                .url(url)
                .build();

        return  request;
    }
}
