package com.android.fileloadersampleapp.model;

import com.google.gson.annotations.SerializedName;

public class ProfileImage {
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("large")
    private String image;
}
