package com.example.android.thijari.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.thijari.R;
import com.example.android.thijari.rest.ThijariService;
import com.example.android.thijari.rest.listener.OnRetrofitResponse;
import com.example.android.thijari.rest.model.FullNewsContentData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class ContentActivity extends BaseActivity {
    private Bundle bundle = null;

    private WebView contentView;
    private WebSettings webViewSettings;
    private ImageView image_header = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String CONTENT_ID = getIntent().getExtras().getString("CONTENT_ID");
        String FROM = getIntent().getExtras().getString("FROM");

        useToolbar(true,FROM);

        contentView = (WebView) findViewById(R.id.content_view);

        image_header = (ImageView) findViewById(R.id.image_header);

        contentView.setPadding(0, 0, 0, 0);

        webViewSettings = contentView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setSupportMultipleWindows(false);
        webViewSettings.setPluginState(WebSettings.PluginState.ON);
        contentView.setLongClickable(false);

        ThijariService.getInstance().getFullContent(CONTENT_ID, new OnRetrofitResponse<List<FullNewsContentData>>() {
            @Override
            public void onResponse(List<FullNewsContentData> response) {
                for (FullNewsContentData contentData : response) {
                    displayFullStory(contentData.getImageURL().get(0).getImgURL(), contentData.getTitle(), contentData.getContentArticle(),
                            contentData.getPublisher(), contentData.getPublisher_image(), contentData.getDatetime());
                }

            }

            @Override
            public void onFailure(ResponseBody errorBody) {

            }
        });

    }

    private void displayFullStory(String imageUrl, String title,
                                  String content, String publisher, String publisher_image,
                                  String datetime) {

        Picasso.with(this).load(imageUrl).fit().into(image_header);

        String customHtml = "<html>"
                + "<head>"
                + "<meta name='viewport'content='width=device-width, user-scalable=no' />"
                + "<style>img{max-width: 100%; height: auto; width: auto;}"
                + " iframe{height: auto; width: auto;}"
                + "p {font-family: 'MyFONT';}"
                + "  @font-face {"
                + "	   font-family: MyFONT;"
                + "	   src: url('file:///android_asset/fonts/OpenSans-Regular.ttf');"
                + "	}"
                + "</style>"
//                +"<script>" +
//                "    window.onload = function() {" +
//                "    var links = document.links;" +
//                "    for(var i=0;i<links.length;i++){" +
//                "        links[i].onclick = function(){Android.showToast(this.href)};" +
//                "      }" +
//                "    }" +
//                "</script>"
                + "</head"
                + ">"
                + "<h2>"
                + title
                + "</h2>"
                + "<hr>"
                + "<img style='width=50; height:50;' src="
                + publisher_image
                + " />"
                + "<p align='left' style='color: #999999; font-size: 11pt'>Published by: "
                + publisher + "<br>Published at " + datetime + "</p><p>"
                + content + "</p></body></html>";

        // wording display incorrectly
        // contentView.loadData(customHtml, "text/html", "UTF-8");
        contentView.loadDataWithBaseURL(null, customHtml, "text/html", "UTF-8",
                null);

        // contentTextViewTitle.setText(Html.fromHtml(title));
        // contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
        // URLImageGetter ReviewImgGetter = new URLImageGetter(getActivity()
        // .getApplicationContext(), contentTextView);
        // Spanned htmlSpan = Html.fromHtml(content, ReviewImgGetter, null);
        // contentTextView.setText(htmlSpan);
        // contentTextViewDate.setText("Publish at " + datetime);
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.content_activity;
    }

    @Override
    protected boolean isMainActivity() {
        return false;
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Intent intent = new Intent(getApplicationContext(),
        // AllNewsListActivity.class);
        // startActivity(intent);
    }

    @Override
    public void onFragmentCallback(Object Data, String from) {

    }
}
