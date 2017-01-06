package com.example.android.thijari.rest;

import android.app.Dialog;
import android.util.Log;

import com.example.android.thijari.rest.listener.OnRetrofitResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus on 12/10/2016.
 */

public abstract class RetrofitResponseHandler<T> implements Callback<T> {

    private final String TAG=this.getClass().getName();
    private OnRetrofitResponse<T> listener;
    private Dialog loadingDialog;

    abstract void handleError(String errorMessage);

    protected RetrofitResponseHandler(Dialog loadingDialog,OnRetrofitResponse listener) {
        this.listener = listener;
        this.loadingDialog = loadingDialog;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
            if(loadingDialog!=null && loadingDialog.isShowing())
                loadingDialog.dismiss();
            if(listener!=null)
                listener.onResponse(response.body());
        }else{
            try {
                if(listener!=null)
                    listener.onFailure(response.errorBody());
                Log.e(TAG,response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG,e.getMessage());
                handleError(e.getMessage());
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        System.out.println("%%%%% error url ="+call.request().url());
        String errorMessage = call.request().url()+" error : "+t.getMessage();
        Log.e(TAG,errorMessage);
        handleError(errorMessage);
    }
}
