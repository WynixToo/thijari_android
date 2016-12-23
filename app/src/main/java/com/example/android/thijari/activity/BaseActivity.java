package com.example.android.thijari.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.thijari.R;
import com.example.android.thijari.listener.FragmentCallback;

/**
 * Created by ANDROID on 20/12/2016.
 */

public abstract class BaseActivity extends AppCompatActivity implements FragmentCallback {

    Toolbar toolbar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
    }

    protected abstract int getLayoutResource();

    private void configureToolbar() {
        if (this.isToolbarShown) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(this.toolbarText);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
//        else {
//            toolbar.setVisibility(View.GONE);
//        }
    }

    boolean isToolbarShown = false;
    String toolbarText = "";

    protected void useToolbar(boolean isToolbarShown, String toolbarText) {
        this.isToolbarShown = isToolbarShown;
        this.toolbarText = toolbarText;
        configureToolbar();
    }

    protected void setupProgressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
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

    protected abstract boolean isMainActivity();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!isMainActivity()) {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
