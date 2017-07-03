package com.ggktech.playstore.playstore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ggktech.playstore.playstore.R;

/**
 * Created by shunryuinik on 03/07/17.
 */

public class ToolsFragment extends Fragment {


    public static ToolsFragment newInstance() {
        return new ToolsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tools,container,false);
        //create this fragment in Navigation activity line  no 35
    }
}
