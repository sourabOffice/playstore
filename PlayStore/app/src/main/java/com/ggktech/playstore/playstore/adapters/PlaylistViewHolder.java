package com.ggktech.playstore.playstore.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggktech.playstore.playstore.R;

/**
 * Created by Sourabh.Wasnik on 7/5/2017.
 */

public class PlaylistViewHolder extends RecyclerView.ViewHolder{
    public TextView playlistTitle;
    public TextView playlistTracks;
    //public ImageView playlistCover;

    public PlaylistViewHolder(View itemView) {
        super(itemView);
        playlistTitle = (TextView)itemView.findViewById(R.id.play_list_name);
        playlistTracks = (TextView)itemView.findViewById(R.id.number_of_tracks);
       // playlistCover = (ImageView)itemView.findViewById(R.id.play_list_cover);
    }
}