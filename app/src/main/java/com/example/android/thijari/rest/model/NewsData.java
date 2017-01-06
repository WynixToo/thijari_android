package com.example.android.thijari.rest.model;

import java.util.List;

/**
 * Created by Asus on 12/18/2016.
 */

public class NewsData {
    private String type;
    private String title;
    private List<ImageUrl> imageURL;
    private String publisher;
    private String publisher_image;
    private String contentID;
    private String datetime;
    private String sponsor;
    private String sponsor_image;

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public List<ImageUrl> getImageURL() {
        return imageURL;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublisher_image() {
        return publisher_image;
    }

    public String getContentID() {
        return contentID;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getSponsor_image() {
        return sponsor_image;
    }
}
