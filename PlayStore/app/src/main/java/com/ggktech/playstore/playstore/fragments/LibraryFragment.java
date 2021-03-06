package com.ggktech.playstore.playstore.fragments;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ggktech.playstore.playstore.R;
import com.ggktech.playstore.playstore.adapters.CustomFragmentPageAdapter;

/**
 * Created by Sourabh.Wasnik on 7/5/2017.
 */
public class LibraryFragment extends Fragment {
    private static final String TAG = LibraryFragment.class.getSimpleName();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CustomFragmentPageAdapter pagerAdapter;

    public LibraryFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        pagerAdapter = new CustomFragmentPageAdapter(getChildFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        FragmentManager pp = getChildFragmentManager();
//        pagerAdapter = new CustomFragmentPageAdapter(pp);
//    }
}