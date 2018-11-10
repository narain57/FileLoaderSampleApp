package com.android.fileloadersampleapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProfileList {

    private ArrayList<Profile> profileArrayList;

    public ArrayList<Profile> getProfileArrayList() {
        return profileArrayList;
    }

    public void setProfileArrayList(ArrayList<Profile> profileArrayList) {
        this.profileArrayList = profileArrayList;
    }
}
