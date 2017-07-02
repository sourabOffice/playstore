package com.ggktech.playstore.playstore.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.ggktech.playstore.playstore.R;
import com.ggktech.playstore.playstore.fragments.ItemListFragment;

/**
 * Created by Sourabh.Wasnik on 6/29/2017.
 */

public class ItemListActivity extends FragmentActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new ItemListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit(); }
    }

}
