package com.ggktech.playstore.playstore.models;

import java.util.UUID;

/**
 * Created by Sourabh.Wasnik on 6/29/2017.
 */

public class Item {

    private String mTitle;
    private String mDescription;

    private boolean mSolved;//new or old

    public Item() {

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

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
