package com.ggktech.playstore.playstore.activities;

import android.support.v4.app.Fragment;

import com.ggktech.playstore.playstore.fragments.ItemListFragment;

/**
 * Created by Sourabh.Wasnik on 6/29/2017.
 */

public class ItemListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ItemListFragment();
    }
}
