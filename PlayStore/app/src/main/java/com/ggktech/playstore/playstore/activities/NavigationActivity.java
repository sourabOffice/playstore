package com.ggktech.playstore.playstore.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ggktech.playstore.playstore.R;
import com.ggktech.playstore.playstore.fragments.GalleryFragment;
import com.ggktech.playstore.playstore.fragments.ItemListFragment;
import com.ggktech.playstore.playstore.fragments.ShareFragment;
import com.ggktech.playstore.playstore.fragments.SlideshowFragment;
import com.ggktech.playstore.playstore.fragments.ToolsFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences settings;
    TextView profileName,profileEmail;

    DrawerLayout drawer;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        String extraEmail;
        if (savedInstanceState == null) {
            extras = getIntent().getExtras();
            if(extras == null) {
                extraEmail= null;
            } else {
                extraEmail= extras.getString("EMAIL_KEY");
            }
        } else {
            extraEmail = (String) savedInstanceState.getSerializable("EMAIL_KEY");
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        replaceThisFragment(new ItemListFragment());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView = navigationView.getHeaderView(0);
       profileName = (TextView) hView.findViewById(R.id.profile_name);
        profileEmail = (TextView) hView.findViewById(R.id.profile_email);
        profileName.setText("Name: " + extraEmail );
        profileEmail.setText(extraEmail);

    }

    private void replaceThisFragment(Fragment receivedFragment) {
        FragmentManager fm = getSupportFragmentManager();
       Fragment fragment = fm.findFragmentById(R.id.fragment_container_updated);
       // if (fragment == null) {
            fragment = receivedFragment;
            fm.beginTransaction()
                    .replace(R.id.fragment_container_updated, fragment)
                    .commit();
        //}
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
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
        } else if(id == R.id.add_item_from_navigation){
            Intent intentAddItemActivity = new Intent(getApplicationContext(),AddItemActivity.class);
            startActivity(intentAddItemActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_list) {
            replaceThisFragment(new ItemListFragment());
            closeDrawer();
        } else if (id == R.id.nav_gallery) {
            replaceThisFragment(new GalleryFragment().newInstance());
            closeDrawer();

            return true;
        } else if (id == R.id.nav_slideshow) {
            replaceThisFragment(new SlideshowFragment().newInstance());
            closeDrawer();

        } else if (id == R.id.nav_tools) {
            replaceThisFragment(new ToolsFragment().newInstance());
            closeDrawer();

        } else if (id == R.id.nav_share) {
            replaceThisFragment(new ShareFragment().newInstance());
            closeDrawer();

        } else if (id == R.id.nav_log_out) {//actually this is log out
            //
            settings = getSharedPreferences("mySharedPref",0);

            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("connected", false);
            editor.commit();

            finish();
//            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void closeDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }


}
