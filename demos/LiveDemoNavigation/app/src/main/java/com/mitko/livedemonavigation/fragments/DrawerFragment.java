package com.mitko.livedemonavigation.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mitko.livedemonavigation.R;
import com.mitko.livedemonavigation.activities.TabsNavigationActivity;
import com.mitko.livedemonavigation.models.DrawerItemInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerFragment extends Fragment {

    private static final String ARG_DRAWER_ITEMS_KEY= "item-key";

    private Drawer.OnDrawerItemClickListener onDrawerItemClickListener;
    private GestureDetector detector;

    public static DrawerFragment createFragment(ArrayList<DrawerItemInfo> drawerItems, Drawer.OnDrawerItemClickListener onDrawerItemClickListener){
        DrawerFragment drawerFragment=new DrawerFragment();
        drawerFragment.setOnDrawerItemClickListener(onDrawerItemClickListener);
        Bundle args=new Bundle();
        args.putSerializable(ARG_DRAWER_ITEMS_KEY, drawerItems);
        drawerFragment.setArguments(args);
        return drawerFragment;
    }

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
        List<PrimaryDrawerItem> items=((ArrayList<DrawerItemInfo>) this.getArguments().getSerializable(ARG_DRAWER_ITEMS_KEY))
                .stream()
                .map(drawer_item_info->new PrimaryDrawerItem()
                        .withIdentifier(drawer_item_info.getId())
                        .withName(drawer_item_info.getTitle())
                ).collect(Collectors.toList());

//        PrimaryDrawerItem item1 = new PrimaryDrawerItem()
//                .withIdentifier(1)
//                .withName(R.string.drawer_item_home)
//                .withIcon(R.drawable.material_drawer_shadow_bottom);
//        SecondaryDrawerItem item2 = new SecondaryDrawerItem()
//                .withIdentifier(2)
//                .withName(R.string.drawer_item_tabs)
//                .withIcon(R.drawable.material_drawer_badge);

        Drawer result = new DrawerBuilder()
                .withActivity(this.getActivity())
                .withToolbar(toolbar)
                .withDrawerItems(new ArrayList<>(items))
//                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
//                    if(drawerItem.getIdentifier()==2){
//                        Intent intent=new Intent(this.getContext(), TabsNavigationActivity.class);
//                        this.startActivity(intent);
//                    }
//                    return true;
//                })
                .withOnDrawerItemClickListener(this.onDrawerItemClickListener)
                .build();

        detector=new GestureDetector(this.getContext(), new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float xDiff=Math.abs(e1.getX()-e2.getX());
                float yDiff=Math.abs(e1.getY()-e2.getY());
               if(e1.getX()<e2.getX()) {
                   result.openDrawer();
               }else{
                   result.closeDrawer();
               }

                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }


    public Drawer.OnDrawerItemClickListener getOnDrawerItemClickListener() {
        return onDrawerItemClickListener;
    }

    public void setOnDrawerItemClickListener(Drawer.OnDrawerItemClickListener onDrawerItemClickListener) {
        this.onDrawerItemClickListener = onDrawerItemClickListener;
    }
}
