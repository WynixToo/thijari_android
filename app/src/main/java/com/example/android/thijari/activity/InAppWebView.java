package com.example.android.thijari.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.android.thijari.R;

/**
 * Created by ANDROID on 21/12/2016.
 */

public class InAppWebView extends BaseActivity {

    private String url = "", pdfname = "";
    private String google_drive_pdf = "https://drive.google.com/viewerng/viewer?embedded=true&url=";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().getExtras().containsKey("URL")) {
            url = getIntent().getExtras().getString("URL");
            pdfname = getIntent().getExtras().getString("PDFNAME");
        }

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        useToolbar(true, pdfname);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.loadUrl(google_drive_pdf + url);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.inapp_webview;
    }

    @Override
    protected boolean isMainActivity() {
        return false;
    }

    @Override
    public void onFragmentCallback(Object Data, String from) {

    }
}
