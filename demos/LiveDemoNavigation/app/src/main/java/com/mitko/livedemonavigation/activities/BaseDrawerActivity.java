package com.mitko.livedemonavigation.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.mitko.livedemonavigation.R;
import com.mitko.livedemonavigation.fragments.DrawerFragment;
import com.mitko.livedemonavigation.models.DrawerItemInfo;

import java.util.ArrayList;

/**
 * Created by dimki on 10.02.2017 г..
 */

public class BaseDrawerActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        this.setupDrawer();
    }

    protected void setupDrawer(){
        ArrayList<DrawerItemInfo> items=new ArrayList<>();

        items.add(new DrawerItemInfo(1, "Books"));
        items.add(new DrawerItemInfo(2, "Tabs"));

        Fragment drawerFragment= DrawerFragment.createFragment(items, (view, position, drawerItem) -> {
            switch ((int)drawerItem.getIdentifier()){
                case 2:
                    Intent intent=new Intent(this, TabsNavigationActivity.class);
                    this.startActivity(intent);
                    break;
            }
            return true;
        } );

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_drawer, drawerFragment)
                .commit();
    }
}
