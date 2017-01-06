package com.example.android.thijari.rest.model;

/**
 * Created by Asus on 12/18/2016.
 */

public class Bookmark {
    private String bookmark_id;
    private String content_id;
    private String mac_id;
    private String access_token;

    public String getBookmark_id() {
        return bookmark_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getContent_id() {
        return content_id;
    }

    public String getMac_id() {
        return mac_id;
    }
}
