package mitko.data;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mitko.data.base.IData;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.attr.id;

/**
 * Created by dimki on 21.02.2017 г..
 */

public class HttpData<T> implements IData<T> {
    private final String url;
    private final OkHttpClient client;
    private final Class<T> classSingle;
    private final Class<T[]> classArray;

    public HttpData(String url, Class<T> classSingle, Class<T[]> classArray) {
        this.url = url;
        this.classSingle = classSingle;
        this.classArray = classArray;
        this.client = new OkHttpClient();
    }

    @Override
    public Observable<T[]> getAll() {
        return Observable.create(new ObservableOnSubscribe<T[]>() {
            @Override
            public void subscribe(ObservableEmitter<T[]> e) throws Exception {
                Request request = buildGetRequest(url);

                Response response = client.newCall(request).execute();

                T[] obj = parseArray(response.body().string());
                e.onNext(obj);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<T> getById(final Object id) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                Request request = buildGetRequest(url + "/" + id);

                Response response = client.newCall(request).execute();

                T obj = parseSingle(response.body().string());
                e.onNext(obj);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<T> add(final T obj) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                Gson gson = new Gson();
                String json = gson.toJson(obj);
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                Response response = client.newCall(request).execute();

                T result = parseSingle(response.body().string());
                e.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private T[] parseArray(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, this.classArray);
    }

    private T parseSingle(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, this.classSingle);
    }

    private Request buildGetRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return request;
    }
}
