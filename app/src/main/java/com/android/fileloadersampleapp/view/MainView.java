package com.android.fileloadersampleapp.view;

import com.android.fileloadersampleapp.model.Profile;

import java.util.ArrayList;

public interface MainView {

    void showProgress();
    void hidProgress();
    void setProfileList(ArrayList<Profile> body);
    void onFailure(Throwable t);
}
