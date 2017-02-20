package mitko.liveworkshopui.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mitko.liveworkshopui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FractionsFragment extends Fragment {


    public FractionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fractions, container, false);
    }

}
