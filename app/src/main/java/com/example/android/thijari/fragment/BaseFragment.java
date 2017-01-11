package com.example.android.thijari.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.thijari.activity.MainActivity2;
import com.example.android.thijari.R;
import com.example.android.thijari.listener.FragmentCallback;


/**
 * Created by ykyee on 10/28/15.
 */
public abstract class BaseFragment extends Fragment {

    protected MainActivity2 activity;
    private FragmentCallback listener;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity2) getActivity();
    }

    protected void setupProgressBar(View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
    }

    protected void isProgressBarShown(boolean isShown) {
        if (progressBar != null) {
            if (isShown) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (FragmentCallback) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    protected void passValueToActivity(Object data, String from) {
        listener.onFragmentCallback(data, from);
    }

}
