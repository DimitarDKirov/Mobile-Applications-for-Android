package mitko.livedemorx;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dimki on 21.02.2017 г..
 */

public class ImagesData {
    Observable<Bitmap> getImage(String url){
        return Observable.create(e -> {
            OkHttpClient client=new OkHttpClient();

            Request request=new Request.Builder()
                    .url(url)
                    .build();

            Response response=client.newCall(request).execute();
            InputStream stream=response.body().byteStream();

            Bitmap bitmap= BitmapFactory.decodeStream(stream);
            e.onNext(bitmap);
        });
    }
}
