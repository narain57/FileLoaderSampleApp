package com.android.fileloadersampleapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile {
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

}
