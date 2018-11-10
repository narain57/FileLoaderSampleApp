package com.android.fileloadersampleapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String username;
    private String id;
    @SerializedName("profile_image")
    private ProfileImage imageUrl;

    public ProfileImage getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ProfileImage imageUrl) {
        this.imageUrl = imageUrl;
    }

}

