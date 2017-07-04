package com.ggktech.playstore.playstore.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ggktech.playstore.playstore.R;
import com.ggktech.playstore.playstore.activities.AddItemActivity;
import com.ggktech.playstore.playstore.dbhelper.DataBaseAdapter;
import com.ggktech.playstore.playstore.models.Item;
import com.ggktech.playstore.playstore.models.ItemSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sourabh.Wasnik on 6/29/2017.
 */

public class ItemListFragment extends Fragment {

    private RecyclerView mItemRecyclerView;
    private ItemAdapter mAdapter;


    DataBaseAdapter dataBaseAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get Instance  of Database Adapter
        dataBaseAdapter = new DataBaseAdapter(getActivity());
        dataBaseAdapter = dataBaseAdapter.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mItemRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        mItemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        //ItemSingleton itemSingleton = ItemSingleton.get(getActivity());
       // List<Item> items = itemSingleton.getItems();

        Cursor mCursor =  dataBaseAdapter.fetchAllItemTableData();

        List<Item> items = new ArrayList<Item>();
        while(mCursor.moveToNext()) {
            Item item = new Item();

            item.setTitle(mCursor.getString(mCursor.getColumnIndex("TITLE")));
            item.setDescription(mCursor.getString(mCursor.getColumnIndex("DESCRIPTION")));
            item.setmItemRating(mCursor.getString(mCursor.getColumnIndex("RATING")));
            item.setmImageUri(Uri.parse(mCursor.getString(mCursor.getColumnIndex("URI"))));
//            Toast.makeText(getActivity(),"ItemFragment updateUI() "+ Uri.parse(mCursor.getString(mCursor.getColumnIndex("URI"))),Toast.LENGTH_LONG).show();


            int checkboxInt= mCursor.getInt(mCursor.getColumnIndex("CHECKBOX"));
            boolean convertedBoolean;
            if (checkboxInt == 1)
                convertedBoolean = true;
            else
                convertedBoolean = false;


            item.setSolved(convertedBoolean);
            items.add(item); //add the item
        }

        //write the code to get items from db

        if (mAdapter == null) {
            mAdapter = new ItemAdapter(items);
            mItemRecyclerView.setAdapter(mAdapter);
        }
            mAdapter.mItems = items;
            mAdapter.notifyDataSetChanged();

    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Item mItem;

        private TextView mTitleTextView;
        private TextView mDescriptionTextView;
        private RatingBar mRatingBar;
        private ImageView mImageView;
        private CheckBox mSolvedCheckBox;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item__item_title_text_view);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.list_item__item_description_text_view);
            mRatingBar =(RatingBar)itemView.findViewById(R.id.rating_bar_list_item);
            mImageView =(ImageView)itemView.findViewById(R.id.imageView_list);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item__item_solved_check_box);
        }

        public void bindItem(Item item) {
            mItem = item;

            mTitleTextView.setText(mItem.getTitle());
            mDescriptionTextView.setText(mItem.getDescription());
            mRatingBar.setRating(Float.parseFloat(mItem.getmItemRating()));
            mImageView.setImageURI(mItem.getmImageUri());
//            Toast.makeText(getActivity(),"ItemListFragment bindItem "+ mItem.getmImageUri(),Toast.LENGTH_LONG).show();

            mSolvedCheckBox.setChecked(mItem.isSolved());
        }

        @Override
        public void onClick(View v) {
            Intent intent = AddItemActivity.newIntent(getActivity(), mItem.getTitle());
            startActivity(intent);

        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        private List<Item> mItems;

        public ItemAdapter(List<Item> items) {
            mItems = items;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item__item, parent, false);
            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item = mItems.get(position);
            holder.bindItem(item);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
}