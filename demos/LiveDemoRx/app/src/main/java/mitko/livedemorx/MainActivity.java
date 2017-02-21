package mitko.livedemorx;

import android.content.Context;
import android.database.Observable;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mitko.livedemorx.models.Superhero;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainActivity act = this;
//        getObservable()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<Date>() {
//                    @Override
//                    public void accept(Date date) throws Exception {
//                        ((TextView) act.findViewById(R.id.tvTest)).setText(date.toString());
//                    }
//                });
        ListView lvSuperheroes = (ListView) this.findViewById(R.id.lvSuperheroes);
        ImageTextArrayAdapter adapter = new ImageTextArrayAdapter(this);
        lvSuperheroes.setAdapter(adapter);

        SuperheroesData data = new SuperheroesData();
        data.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Superhero[]>() {
                    @Override
                    public void accept(Superhero[] superheros) throws Exception {
                        adapter.clear();
                        adapter.addAll(superheros);
                    }
                });
    }

//    io.reactivex.Observable<Date> getObservable() {
//
//        return io.reactivex.Observable.create(new ObservableOnSubscribe<Date>() {
//
//            @Override
//            public void subscribe(ObservableEmitter<Date> e) throws Exception {
//                while (true) {
//                    SystemClock.sleep(1000);
//                    e.onNext(new Date());
//                }
//            }
//        });
//    }

    public class ImageTextArrayAdapter extends ArrayAdapter<Superhero> {

        public ImageTextArrayAdapter(Context context) {
            super(context, -1);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Superhero superhero = this.getItem(position);

            View view = convertView;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.irem_superhero, parent, false);
            }

            ((TextView) view.findViewById(R.id.tvName)).setText(superhero.getName());
            ImageView iv = (ImageView) view.findViewById(R.id.iv);
            this.SetImageToImageView(iv, superhero.getImgUrl());

            return view;
        }

        void SetImageToImageView(ImageView iv, String imgUrl) {
            if (imgUrl == null) {
                return;
            }

            ImagesData imageData = new ImagesData();
            imageData.getImage(imgUrl)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(bitmap -> {
                        iv.setImageBitmap(bitmap);
                    });
        }
    }
}
