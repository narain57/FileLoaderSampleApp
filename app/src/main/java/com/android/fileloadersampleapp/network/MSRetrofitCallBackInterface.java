package com.android.fileloadersampleapp.network;

import retrofit2.Response;

interface MSRetrofitCallBackInterface<T> {

    void onSuccess(Response<T> response);
    void onFailure(Throwable t);
}
