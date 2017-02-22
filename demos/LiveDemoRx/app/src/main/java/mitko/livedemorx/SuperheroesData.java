package mitko.livedemorx;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import mitko.livedemorx.models.Superhero;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dimki on 21.02.2017 г..
 */

public class SuperheroesData {
   private String ulr = "http://192.168.0.104:3001/superheroes";
//    private String ulr = "http://10.82.200.82:3001/superheroes";

    Observable<Superhero[]> getAll() {
        return Observable.create(e -> {
            OkHttpClient client = new OkHttpClient();
            Request req = new Request.Builder()
                    .url(this.ulr)
                    .build();
            Response res = client.newCall(req).execute();
            String json = res.body().string();
            Gson gson = new Gson();
            Superhero[] superheroes = gson.fromJson(json, Superhero[].class);
            e.onNext(superheroes);
        });
    }
}

