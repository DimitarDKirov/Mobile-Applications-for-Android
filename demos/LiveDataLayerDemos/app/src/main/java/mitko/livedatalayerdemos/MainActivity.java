package mitko.livedatalayerdemos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import mitko.data.HttpData;
import mitko.data.base.IData;
import mitko.data.models.Superhero;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://10.82.200.82:3001/superheroes";
        final IData<Superhero> data = new HttpData<>(url, Superhero.class, Superhero[].class);
        final MainActivity activity = this;
        data.add(new Superhero("", "Thor", "Thor Odisson", "http://static.srcdn.com/wp-content/uploads/thor-3-ragnarok-chris-hemsworth.jpg"))
                .switchMap(new Function<Superhero, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Superhero superhero) throws Exception {
                        return data.getAll();
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object superheroes) throws Exception {
                        StringBuilder builder = new StringBuilder();
                        for (Superhero hero : (Superhero[]) superheroes) {
                            builder.append(hero.getName());
                            builder.append(", ");
                        }

                        Toast.makeText(activity, builder.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
