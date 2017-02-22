package com.minkov.sqlitedemos;

import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.minkov.sqlitedemos.models.Power;
import com.minkov.sqlitedemos.models.Superhero;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;

import java.util.List;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //presenter.load();
        Power[] powers={
                new Power("Intelligence"),
                new Power("Suit")
        };

        Superhero hero=new Superhero("Batman", powers);
        hero.save();    //==SugarRecord.save(hero)

        List<Superhero> heroes=Superhero.listAll(Superhero.class);

        for(Superhero sh:heroes){
            StringBuilder builder=new StringBuilder();
            if(sh.getPowers()!=null) {
                for (Power power : sh.getPowers()) {
                    builder.append(power.getName());
                    builder.append(", ");
                }
            }
            String message=String.format("%d, %s, %s", sh.getId(), sh.getName(), builder.toString());
            Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
        }
    }

    void update(){
        //var data = this.presenter.getData();
        //update UI
    }

    class Presenter {

        View view;

        void load(){
            //async operation
            //ready -> view.update();
        }
    }
}

