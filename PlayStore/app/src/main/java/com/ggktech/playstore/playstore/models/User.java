package com.ggktech.playstore.playstore.models;

import java.util.UUID;

/**
 * Created by Sourabh.Wasnik on 7/1/2017.
 */

public class User {

    private String mEmail;
    private String mPassword;

    public User() {

    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
