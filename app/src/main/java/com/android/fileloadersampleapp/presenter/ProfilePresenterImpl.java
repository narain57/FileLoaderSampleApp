package com.android.fileloadersampleapp.presenter;

import com.android.fileloadersampleapp.model.Profile;
import com.android.fileloadersampleapp.network.OnApiCallCompleted;
import com.android.fileloadersampleapp.network.ProfileInteractor;
import com.android.fileloadersampleapp.view.MainView;

import java.util.ArrayList;

import retrofit2.Response;

public class ProfilePresenterImpl implements ProfilePresenter,OnApiCallCompleted {


    private MainView view;
    private ProfileInteractor interactor;

    public ProfilePresenterImpl(MainView view, ProfileInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void fetchProfileDetails() {
        if(view !=null)
            view.showProgress();
        interactor.fetchProfileList(this);
    }

    @Override
    public void onSuccess(Response response) {
        if(view !=null) {
            view.hidProgress();
            view.setProfileList((ArrayList<Profile>)response.body());
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(view !=null) {
            view.hidProgress();
            view.onFailure(t);
        }
    }
}

