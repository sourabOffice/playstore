package com.ggktech.playstore.playstore.models;

/**
 * Created by Sourabh.Wasnik on 7/5/2017.
 */

public class PlaylistObject {
    String playlistTitle;
    String playlistTracks;
    String info;//supposed to be an image

    public PlaylistObject(String PlaylistTitle, String playlistTrack, String info) {
        this.playlistTitle = PlaylistTitle;
        this.playlistTracks = playlistTrack;
        this.info = info;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public String getPlaylistTracks() {
        return playlistTracks;
    }

    public String getInfo() {
        return info;
    }

}
