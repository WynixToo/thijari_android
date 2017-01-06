package com.example.android.thijari.rest.model;

import java.util.List;

/**
 * Created by Asus on 12/15/2016.
 */

public class MagazineContent {
    private String type;
    private String content_type;
    private String item_link;
    private String title;
    private String item_size;
    private String contentArticle;
    private List<ImageUrl> imageURL;
    private String publisher;
    private String publisher_image;
    private String author;
    private String datetime;
    private String sponsor;
    private String sponsor_image;

    public String getType() {
        return type;
    }

    public String getContent_type() {
        return content_type;
    }

    public String getItem_link() {
        return item_link;
    }

    public String getTitle() {
        return title;
    }

    public String getItem_size() {
        return item_size;
    }

    public String getContentArticle() {
        return contentArticle;
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

    public String getAuthor() {
        return author;
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
