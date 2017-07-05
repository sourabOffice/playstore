package com.ggktech.playstore.playstore.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sourabh.Wasnik on 6/29/2017.
 */

public class ItemSingleton {
    private static ItemSingleton sItemSingleton;

    private List<Item> mItems;

    public static ItemSingleton get(Context context) {
        if (sItemSingleton == null) {
            sItemSingleton = new ItemSingleton(context);
        }
        return sItemSingleton;
    }

    private ItemSingleton(Context context) {
        mItems = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            Item item = new Item();
//            item.setTitle("Item #" + i);
//            item.setDescription("Stupid playlistTrack " + i);
//            item.setSolved(i % 2 == 0);
////             Every other one
//            mItems.add(item);
//        }
    }

    public void addItem(Item item) {
        mItems.add(item);
    }

    public List<Item> getItems() {
        return mItems;
    }

    public Item getItem(UUID id) {
        for (Item item : mItems) {
//            if (item.getId().equals(id)) {
//                return item;
//            }
        }
        return null;
    }

}
