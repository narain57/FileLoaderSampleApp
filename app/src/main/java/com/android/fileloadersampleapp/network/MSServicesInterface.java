package com.android.fileloadersampleapp.network;

import com.android.fileloadersampleapp.model.Profile;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MSServicesInterface {

    @GET
    Call<ArrayList<Profile>> fetchProfileDetails(@Url String url);
}
