package mitko.liveworkshopui.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import mitko.liveworkshopui.R;
import mitko.liveworkshopui.SuperheroesApplication;
import mitko.liveworkshopui.adapters.SuperheroesAdapter;
import mitko.liveworkshopui.models.Superhero;
import mitko.liveworkshopui.tasks.HttpGetAsyncTask;
import mitko.liveworkshopui.ui.LoadingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroesListFragment extends Fragment {


    private String apiUrl;
    private ArrayList<Superhero> superheroes;
    private SuperheroesAdapter superheroesAdapter;
    private ListView lvSuperheroes;
    private LoadingFragment loadingFragment;

    public SuperheroesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate  the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_superheroes_list, container, false);
        this.apiUrl=((SuperheroesApplication) this.getActivity().getApplication()).getApiBasUrl()+"superheroes";

        this.superheroes = ((SuperheroesApplication) this.getActivity().getApplication()).getSuperheroes();
        this.superheroesAdapter = new SuperheroesAdapter(this.getContext(), this.superheroes);
        this.lvSuperheroes = (ListView) root.findViewById(R.id.lvSuperheroes);
        this.lvSuperheroes.setAdapter(this.superheroesAdapter);

        this.lvSuperheroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Tapped on " + superheroes.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        this.loadingFragment = LoadingFragment.create(this.getContext());
        this.load();

        return root;
    }

    private void load() {
        this.loadingFragment.show();

        new HttpGetAsyncTask<>(Superhero[].class, new HttpGetAsyncTask.OnDataReady<Superhero[]>() {
            @Override
            public void onReady(final Superhero[] items) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        superheroesAdapter.clear();
                        superheroesAdapter.addAll(new ArrayList<>(Arrays.asList(items)));
                        loadingFragment.hide();
                    }
                });
            }
        }).execute(this.apiUrl);
    }
}
