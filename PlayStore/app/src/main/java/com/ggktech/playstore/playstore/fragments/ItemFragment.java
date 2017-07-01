package com.ggktech.playstore.playstore.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.ggktech.playstore.playstore.R;
import com.ggktech.playstore.playstore.activities.AddItemActivity;
import com.ggktech.playstore.playstore.activities.ItemListActivity;
import com.ggktech.playstore.playstore.activities.NavigationActivity;
import com.ggktech.playstore.playstore.activities.SwipeActivity;
import com.ggktech.playstore.playstore.dbhelper.DBHelper;
import com.ggktech.playstore.playstore.dbhelper.DataBaseAdapter;
import com.ggktech.playstore.playstore.models.Item;
import com.ggktech.playstore.playstore.models.ItemSingleton;

import java.util.UUID;

/**
 * Created by Sourabh.Wasnik on 6/29/2017.
 */

public class ItemFragment extends Fragment {

    private static final String ARG_ITEM_ID = "item_id";

    private Item mItem;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private RatingBar ratingBar;
    private CheckBox mSolvedCheckBox;
    private Button mAddButton;
    static FragmentTransaction fragmentTransaction;
    static FragmentManager fm;
    DataBaseAdapter dataBaseAdapter;

    public static ItemFragment newInstance(UUID itemId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_ID, itemId);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get Instance  of Database Adapter
        dataBaseAdapter =new DataBaseAdapter(getActivity());
        dataBaseAdapter = dataBaseAdapter.open();

        UUID itemId = (UUID) getArguments().getSerializable(ARG_ITEM_ID);
        mItem = ItemSingleton.get(getActivity()).getItem(itemId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item, container, false);

        mTitleField = (EditText) v.findViewById(R.id.item_title);
        if (mItem != null)
            mTitleField.setText(mItem.getTitle());

        mDescriptionField = (EditText) v.findViewById(R.id.item_description);

//        mTitleField.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // This space intentionally left blank
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mItem.setTitle(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // This one too
//            }
//        });

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.item_solved);
        if (mItem != null)
            mSolvedCheckBox.setChecked(mItem.isSolved());

//        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // Set the item's solved property
//                mItem.setSolved(isChecked);
//            }
//        });

        mAddButton = (Button) v.findViewById(R.id.item_add);
        //mAddButton.setEnabled(false);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTitleField.getText().toString() != null && mDescriptionField.getText().toString() != null && !mTitleField.getText().toString().equals("") && !mDescriptionField.getText().toString().equals("")) {
                    Item item = new Item();
                    item.setTitle(mTitleField.getText().toString());
                    item.setDescription(mDescriptionField.getText().toString());
                    item.setSolved(mSolvedCheckBox.isChecked());

                    dataBaseAdapter.insertEntryIntoItemTable(item);

                    ItemSingleton.get(getActivity()).addItem(item);//using singleton to access list from everywhere

                    Intent intentItemList = new Intent(getActivity(), NavigationActivity.class);
                    startActivity(intentItemList);

                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Please add title and description", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseAdapter.close();
    }
}
