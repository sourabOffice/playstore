package com.ggktech.playstore.playstore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ggktech.playstore.playstore.R;

/**
 * Created by Sourabh.Wasnik on 7/4/2017.
 */

public class SlideshowFragment extends Fragment {
    public static SlideshowFragment newInstance() {
        return new SlideshowFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slideshow,container,false);
        //create this fragment in Navigation activity line  no 35
    }
}
