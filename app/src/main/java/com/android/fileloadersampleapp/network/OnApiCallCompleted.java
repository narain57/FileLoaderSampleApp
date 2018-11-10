package com.android.fileloadersampleapp.network;

import retrofit2.Response;

public interface OnApiCallCompleted {

    void onSuccess(Response response);
    void onFailure(Throwable t);
}
