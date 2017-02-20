package mitko.liveworkshopui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mitko.liveworkshopui.R;
import mitko.liveworkshopui.models.Superhero;

/**
 * Created by dimki on 18.02.2017 г..
 */
public class SuperheroesAdapter extends ArrayAdapter<Superhero>{
    public SuperheroesAdapter(Context context) {
        this(context, new ArrayList<Superhero>());
    }

    public SuperheroesAdapter(Context context, ArrayList<Superhero> list) {
        super(context, -1, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.item_superhero, parent, false);
        }

        Superhero superhero=this.getItem(position);
        ((TextView)view.findViewById(R.id.tvSuperheroName)).setText(superhero.getName());
        ((TextView)view.findViewById(R.id.tvSuperheroIdentity)).setText(superhero.getSecretIdentity());
        return view;
    }
}
