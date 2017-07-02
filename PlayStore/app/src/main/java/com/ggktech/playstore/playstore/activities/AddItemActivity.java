package com.ggktech.playstore.playstore.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.ggktech.playstore.playstore.R;
import com.ggktech.playstore.playstore.fragments.ItemFragment;

import java.util.UUID;

public class AddItemActivity extends SingleFragmentActivity {

    private static final String EXTRA_ITEM_TITLE = "com.ggktech.playstore.playstore.item_title";

    public static Intent newIntent(Context packageContext, String secretTitle) {
        Intent intent = new Intent(packageContext, AddItemActivity.class);
        intent.putExtra(EXTRA_ITEM_TITLE, secretTitle);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String itemTitle = (String) getIntent().getSerializableExtra(EXTRA_ITEM_TITLE);
        return ItemFragment.newInstance(itemTitle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
