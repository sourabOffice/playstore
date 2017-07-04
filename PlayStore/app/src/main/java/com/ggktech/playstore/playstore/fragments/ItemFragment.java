package com.ggktech.playstore.playstore.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.ImageView;
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

import static android.app.Activity.RESULT_OK;

/**
 * Created by Sourabh.Wasnik on 6/29/2017.
 */

public class ItemFragment extends Fragment {

    private static final String ARG_ITEM_TITLE = "item_title";

    private Item mItem;
    private ImageView mImageView;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private RatingBar mRatingBarFragmentItem;
    private CheckBox mSolvedCheckBox;
    private Button mAddButton;
    static FragmentTransaction fragmentTransaction;
    static FragmentManager fm;
    DataBaseAdapter dataBaseAdapter;
    boolean comingFromList;
    Uri savedUri;

    public static ItemFragment newInstance(String secretTitle) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_TITLE, secretTitle);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get Instance  of Database Adapter
        dataBaseAdapter = new DataBaseAdapter(getActivity());
        dataBaseAdapter = dataBaseAdapter.open();

        String itemTitle = (String) getArguments().getSerializable(ARG_ITEM_TITLE);

//        mItem = ItemSingleton.get(getActivity()).getItem(itemId);

        //here comes the mItem from Database
        if (itemTitle != null && !itemTitle.equals(""))
            mItem = dataBaseAdapter.getSingleEntryITEM(itemTitle);

        if (mItem != null) {
            comingFromList = true;
        } else {
            comingFromList = false;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item, container, false);


        mRatingBarFragmentItem = (RatingBar)v.findViewById(R.id.rating_bar_fragment_item);
        mTitleField = (EditText) v.findViewById(R.id.item_title);
        mDescriptionField = (EditText) v.findViewById(R.id.item_description);

        if (mItem != null)
            mTitleField.setText(mItem.getTitle());
        if (mItem != null)
            mDescriptionField.setText(mItem.getDescription());

        if(mItem != null)
            mRatingBarFragmentItem.setRating(Float.parseFloat(mItem.getmItemRating()));

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

        mImageView = (ImageView)v.findViewById(R.id.imageview_item);
        if (mItem != null){

//            Toast.makeText(getActivity(),"ItemFragment mItem is not null "+ mItem.getmImageUri(),Toast.LENGTH_LONG).show();
                mImageView.setImageResource(R.drawable.ic_menu_camera);
            mImageView.setImageURI(mItem.getmImageUri());
        }

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"), 1);
            }
        });



        mAddButton = (Button) v.findViewById(R.id.item_add);
        //mAddButton.setEnabled(false);
        if(comingFromList)
            mAddButton.setText("Delete");

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!comingFromList) {
                    if (mTitleField.getText().toString() != null && mDescriptionField.getText().toString() != null && !mTitleField.getText().toString().equals("") && !mDescriptionField.getText().toString().equals("")) {
                        Item item = new Item();

                        item.setmItemRating(String.valueOf(mRatingBarFragmentItem.getRating()));
                        item.setTitle(mTitleField.getText().toString());
                        item.setDescription(mDescriptionField.getText().toString());
                        item.setmImageUri(savedUri);//check

                        item.setSolved(mSolvedCheckBox.isChecked());

                        dataBaseAdapter.insertEntryIntoItemTable(item);

                        //ItemSingleton.get(getActivity()).addItem(item);//using singleton to access list from everywhere

                        Intent intentItemList = new Intent(getActivity(), NavigationActivity.class);
                        startActivity(intentItemList);

                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), "Please add title and description", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //do the delete code
                    if (mTitleField.getText().toString() != null && mDescriptionField.getText().toString() != null && !mTitleField.getText().toString().equals("") && !mDescriptionField.getText().toString().equals("")) {
                        dataBaseAdapter.deleteEntryItem(mTitleField.getText().toString());
                        getActivity().finish();
                    }
                }
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                savedUri = data.getData();
                mImageView.setImageURI(savedUri);//OK
//                Toast.makeText(getActivity(),"ItemFragment onactivityresult"+ savedUri,Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseAdapter.close();
    }
}
