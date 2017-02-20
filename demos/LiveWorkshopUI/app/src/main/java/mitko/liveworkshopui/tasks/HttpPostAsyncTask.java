package mitko.liveworkshopui.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dimki on 19.02.2017 г..
 */

public class HttpPostAsyncTask<T> extends HttpAsyncTask<  T> {


    private final T data;

    public HttpPostAsyncTask(T data, Class<T> klass, OnDataReady<T> onDataReady) {
        super(klass, onDataReady);
        this.data = data;
    }

    @Override
    protected Request buildRequest(String url) {
        String json = new Gson().toJson(this.data);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return request;
    }
}
