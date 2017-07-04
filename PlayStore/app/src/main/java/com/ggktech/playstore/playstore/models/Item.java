package com.ggktech.playstore.playstore.models;

import android.net.Uri;

import java.util.UUID;

/**
 * Created by Sourabh.Wasnik on 6/29/2017.
 */

public class Item {

    private String mTitle;
    private String mDescription;
    private String mItemRating;
    private Uri mImageUri;
    private boolean mSolved;//new or old

    public Item() {

    }

    public Uri getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmItemRating() {
        return mItemRating;
    }

    public void setmItemRating(String mItemRating) {
        this.mItemRating = mItemRating;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
