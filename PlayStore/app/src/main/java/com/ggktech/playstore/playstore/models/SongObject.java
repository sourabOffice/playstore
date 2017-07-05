package com.ggktech.playstore.playstore.models;

import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by Sourabh.Wasnik on 7/5/2017.
 */

public class SongObject {
    String songTitle;
    String songAuthor;
    String uri;// imageview

    public SongObject(String songTitle, String songAuthor,String uri) {
        this.songTitle = songTitle;
        this.songAuthor = songAuthor;
        this.uri = uri;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongAuthor() {
        return songAuthor;
    }
}
