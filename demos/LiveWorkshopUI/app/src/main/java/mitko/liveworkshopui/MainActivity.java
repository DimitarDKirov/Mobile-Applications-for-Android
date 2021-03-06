package mitko.liveworkshopui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import mitko.liveworkshopui.models.Superhero;
import mitko.liveworkshopui.tabs.AboutFragment;
import mitko.liveworkshopui.tabs.FractionsFragment;
import mitko.liveworkshopui.tabs.SearchFragment;
import mitko.liveworkshopui.tabs.SuperheroesListFragment;
import mitko.liveworkshopui.tasks.HttpAsyncTask;
import mitko.liveworkshopui.tasks.HttpPostAsyncTask;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Context context = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDialog dialog = new MaterialDialog.Builder(context)
                        .title("Add superhero")
                        .customView(R.layout.modal_add, true)
                        .positiveText("Save")
                        .negativeText("Cancel")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                View modal = dialog.getCustomView();

                                String name = ((EditText) modal.findViewById(R.id.etSuperheroName)).getText().toString();
                                String secretIdentity = ((EditText) modal.findViewById(R.id.etSuperheroIdentity)).getText().toString();

                                Superhero superhero = new Superhero(name, secretIdentity);
                                createSuperhero(superhero);
                            }
                        })
                        .build();
                dialog.show();
            }
        });

    }

    public void createSuperhero(Superhero superhero) {
        String url = ((SuperheroesApplication) this.getApplication()).getApiBasUrl() + "superheroes";
        final Context context = this;
        new HttpPostAsyncTask<Superhero>(superhero, Superhero.class, new HttpAsyncTask.OnDataReady<Superhero>() {
            @Override
            public void onReady(Superhero data) {
                Toast.makeText(context, "Created!", Toast.LENGTH_SHORT).show();
            }
        }).execute(url);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SuperheroesListFragment();
                case 1:
                    return new FractionsFragment();
                case 2:
                    return new SearchFragment();
                case 3:
                    return new AboutFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HEROES";
                case 1:
                    return "FRACTIONS";
                case 2:
                    return "SEARCH";
                case 3:
                    return "ABOUT";
            }
            return null;
        }
    }
}
