package com.ggktech.playstore.playstore.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.ggktech.playstore.playstore.fragments.ItemListFragment;
import com.ggktech.playstore.playstore.fragments.PlaylistFragment;
import com.ggktech.playstore.playstore.fragments.SongFragment;
import com.ggktech.playstore.playstore.fragments.ToolsFragment;

/**
 * Created by Sourabh.Wasnik on 7/5/2017.
 */
public class CustomFragmentPageAdapter extends FragmentPagerAdapter {
    //private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();
    private static final int FRAGMENT_COUNT = 4;
    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ItemListFragment();
            case 1:
                return new PlaylistFragment();
            case 2:
                return new SongFragment();
            case 3:
                return new ToolsFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "ItemList";
            case 1:
                return "Playlists";
            case 2:
                return "Songs";
            case 3:
                return "AlbumsTOOLS";
            case 4:
                return "Artists";
        }
        return null;
    }
}