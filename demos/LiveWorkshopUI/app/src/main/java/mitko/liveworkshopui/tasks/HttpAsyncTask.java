package mitko.liveworkshopui.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dimki on 19.02.2017 г..
 */

public abstract class HttpAsyncTask<T> extends AsyncTask<String, String, T> {
    private final OnDataReady<T> onDataReady;
    private final Class<T> classType;

    public HttpAsyncTask(Class<T> klass, OnDataReady<T> onDataReady) {
        this.onDataReady = onDataReady;
        this.classType = klass;
    }

    @Override
    protected T doInBackground(String... params) {
        String url=params[0];
        OkHttpClient client=new OkHttpClient();
        Request request=this.buildRequest(url);

        try {
            Response response=client.newCall(request).execute();
            String json=response.body().string();
            Gson gson=new Gson();
            T data=gson.fromJson(json,getClassType());
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected abstract Request buildRequest(String url);

    @Override
    protected void onPostExecute(T data) {
        this.onDataReady.onReady(data);
    }

    protected Class<T> getClassType() {
        return classType;
    }

    public interface OnDataReady<T> {
        void onReady(T data);
    }
}
