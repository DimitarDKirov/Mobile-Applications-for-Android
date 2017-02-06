package com.mitko.testactivities.AdapterViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mitko.testactivities.R;
import com.mitko.testactivities.data.Superhero;

public class AdapterViewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_views);

        String[] names={"Mitko", "Pesho","Alexander"};

        ListView lvNames=(ListView)this.findViewById(R.id.lv_names);
        lvNames.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names));

        Superhero[] superheroes={
                new Superhero("bat", "Batman","Bruce"),
                new Superhero("blw","Black Widow", "Natasha")
        };
        ListView lvHeroes=(ListView)this.findViewById(R.id.lv_superheroes);
        lvHeroes.setAdapter(new ArrayAdapter<Superhero>(this, -1, superheroes){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view =inflater.inflate(R.layout.item_superhero, null);

                TextView tvname=(TextView) view.findViewById(R.id.tv_superhero_name);
                Superhero hero=this.getItem(position);
                tvname.setText(hero.getName());

                TextView tvidentity=(TextView) view.findViewById(R.id.tv_superhero_identity);
                tvidentity.setText(hero.getSecterIdentity());
                return  view;
            }
        });

        lvHeroes.setOnItemClickListener(((parent, view, position, id) -> {
            TextView tvName=(TextView)view.findViewById(R.id.tv_superhero_name);
            Toast.makeText(this, "Clicked "+tvName.getText().toString(),Toast.LENGTH_SHORT).show();
        }));
    }
}
