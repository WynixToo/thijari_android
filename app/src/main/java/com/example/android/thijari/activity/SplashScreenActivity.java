package com.example.android.thijari.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.android.thijari.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreenActivity extends AppCompatActivity {
    /**
     * The number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (isStoragePermissionGranted()) {
                    Intent i = new Intent(SplashScreenActivity.this, MainMenuActivity.class);
                    startActivity(i);
                    SplashScreenActivity.this.finish();
                }


                // close this activity
                finish();
            }
        }, AUTO_HIDE_DELAY_MILLIS);

    }

    int REQUEST_CODE_CONTACT = 101;
    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {

            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
//                Log.v(TAG,"Permission is granted");
                return true;
            } else {

//                Log.v(TAG,"Permission is revoked");
                this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
//            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
//            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        } else {
            Bundle b = new Bundle();
//            if (FIRSTLAUNCH == true) {
//                FIRSTLAUNCH = false;
//                b.putString("result", "FIRSTLAUNCH");
//                // ViewController.getInstance(this).openView(
//                // ViewName.CATEGORYVIEW, b, CAT_RESULT_CODE);
//                // overridePendingTransition(R.anim.fade, R.anim.fade_out);
//                // SplashActivity.this.finish();
//                // } else {
//            }

            Intent i = new Intent(SplashScreenActivity.this, MainMenuActivity.class);
            startActivity(i);
            this.finish();
        }
    }


}
