package com.example.android.thijari.rest.model;


import java.util.List;

/**
 * Created by Asus on 12/18/2016.
 */

public class NextPageObject {
    private List<NewsData> data;
    private String next_page_url;
    private String prev_page_url;

    public List<NewsData> getData() {
        return data;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }
}
