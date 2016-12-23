package com.example.android.thijari.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ANDROID on 21/12/2016.
 */

public class CommonUtil {

    int filelength = 0;
    private Context context = null;
    private static CommonUtil _instance = null;

    private CommonUtil(Context context) {
        if (context == null)
            return;
        this.context = context;
    }

    public static CommonUtil getInstance(Context context) {
        if (_instance != null && context != null)
            _instance.context = context;

        return _instance = _instance == null ? new CommonUtil(context)
                : _instance;
    }

    public interface PDFDataCallback {
        public void onPDFDataCallback(Object object);
    }

    public void getRawFileSize(final String fileurl, final PDFDataCallback callback) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    URL url = new URL(fileurl);
                    URLConnection conection = url.openConnection();
                    conection.connect();
                    filelength = conection.getContentLength();
                    // url.openStream();
                    if (callback != null)
                        callback.onPDFDataCallback(filelength);
                    // filelength = ;
                    // conection.getContentLength();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }).start();
        // return filelength;
    }

    public boolean isPDFExisting(String fname) {
        String fullPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/WebsightCacheFiles/pdf";
        File file = new File(fullPath, fname);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

}
