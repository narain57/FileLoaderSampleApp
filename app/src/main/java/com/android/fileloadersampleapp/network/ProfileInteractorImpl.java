package com.android.fileloadersampleapp.network;

import com.android.fileloadersampleapp.model.Profile;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class ProfileInteractorImpl implements ProfileInteractor {

    @Override
    public void fetchProfileList(final OnApiCallCompleted listener) {

        final Call<ArrayList<Profile>> profileDetailsCall = NetworkHandler.getClientInstance().fetchProfileDetails("/raw/wgkJgazE");

        RetrofitCallback callback = new RetrofitCallback(new MSRetrofitCallBackInterface<ArrayList<Profile>>() {
            @Override
            public void onSuccess(Response<ArrayList<Profile>> response) {
                listener.onSuccess(response);
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
        profileDetailsCall.enqueue(callback);

    }
}
