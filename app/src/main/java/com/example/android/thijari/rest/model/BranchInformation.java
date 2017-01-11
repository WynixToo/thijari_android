package com.example.android.thijari.rest.model;

/**
 * Created by Asus on 1/6/2017.
 */

public class BranchInformation {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String fax;
    private String email;
    private String operation_hours;
    private String remarks;
    private String distance;
    private String latitude;
    private String longitude;
//    private BranchLocation location;


    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getDistance() {
        return distance;
    }

    public String getEmail() {
        return email;
    }

    public String getFax() {
        return fax;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOperation_hours() {
        return operation_hours;
    }

    public String getPhone() {
        return phone;
    }

    public String getRemarks() {
        return remarks;
    }
}