package com.mitko.livedemonavigation.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mitko.livedemonavigation.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerFragment extends Fragment {


    public DrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        this.setupDrawer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drawer, container, false);
    }

    private void setupDrawer() {
        //https://github.com/mikepenz/MaterialDrawer

        Toolbar toolbar=(Toolbar)this.getView().findViewById(R.id.drawer_toolbar);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName(R.string.drawer_item_home)
                .withIcon(R.drawable.material_drawer_shadow_bottom);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem()
                .withIdentifier(2)
                .withName(R.string.drawer_item_settings)
                .withIcon(R.drawable.material_drawer_badge);

        Drawer result = new DrawerBuilder()
                .withActivity(this.getActivity())
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2)
                .build();
    }

}
