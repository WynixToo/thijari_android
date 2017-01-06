package com.example.android.thijari.rest.model;

/**
 * Created by Asus on 1/6/2017.
 */

public class BranchInformation {
    private String name;
    private String tel;
    private String fax;
    private String email;
    private String waktuOperasi;
    private BranchLocation location;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public BranchLocation getLocation() {
        return location;
    }

    public void setLocation(BranchLocation location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWaktuOperasi() {
        return waktuOperasi;
    }

    public void setWaktuOperasi(String waktuOperasi) {
        this.waktuOperasi = waktuOperasi;
    }
}
