package com.example.android.thijari.rest.model;


import java.util.List;

/**
 * Created by Asus on 12/14/2016.
 */

public class Magazine {
    private String categoryID;
    private String categoryString;
    private List<Thumbnail> thumbnail;
    private String sponsor;
    private String sponsor_image;
    private List<MagazineData> magazine_list;

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryString() {
        return categoryString;
    }

    public List<Thumbnail> getThumbnail() {
        return thumbnail;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getSponsor_image() {
        return sponsor_image;
    }

    public List<MagazineData> getMagazine_list() {
        return magazine_list;
    }
}
