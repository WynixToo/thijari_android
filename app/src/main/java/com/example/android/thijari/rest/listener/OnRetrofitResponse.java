package com.example.android.thijari.rest.listener;

import okhttp3.ResponseBody;

/**
 * Created by Asus on 12/10/2016.
 */

public interface OnRetrofitResponse<T> {

    void onResponse(T response);
    void onFailure(ResponseBody errorBody);
}
