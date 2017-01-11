package com.example.android.thijari.rest;

import com.example.android.thijari.rest.model.Bookmark;
import com.example.android.thijari.rest.model.BranchInformation;
import com.example.android.thijari.rest.model.Feed;
import com.example.android.thijari.rest.model.FullNewsContentData;
import com.example.android.thijari.rest.model.Magazine;
import com.example.android.thijari.rest.model.MagazineContent;
import com.example.android.thijari.rest.model.MagazineListData;
import com.example.android.thijari.rest.model.NewsData;
import com.example.android.thijari.rest.model.NextPageObject;
import com.example.android.thijari.rest.model.SubCategoryData;
import com.example.android.thijari.rest.model.UserData;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * Created by Asus on 12/10/2016.
 */

public interface ThijariAPI {

    @GET("applogin.php")
    Call<UserData> getTokenByLogin(@Query("mac_id") String mac_id);

    @GET("endpointv2.php?action=mainmenu")
    Call<List<Feed>> getMainPageFeed();

    @GET("endpoint_pagination.php?action=list") //check
    Call<List<NewsData>> getNewsList(@Query("category_id") String cat_id);

    @GET("endpoint_pagination.php")
    Call<NextPageObject> getNextOrPreviousNewsList(@QueryMap Map<String, String> urlParameter);

    @GET("endpoint_pagination.php?action=subcategory")
    Call<List<SubCategoryData>> getSubFeedList(@Query("parent_category_id") String cat_id);

    @GET("endpoint.php?action=content")//check if not content_id
    Call<List<FullNewsContentData>> getFullContent(@Query("content_id") String con_id);

    @GET("endpoint.php?action=magazine_mainmenu")
    Call<List<Magazine>> getMainMagazine();

    @GET("endpoint.php?action=magazine_list")
    Call<List<MagazineListData>> getMagazineList(@Query("category_id") String cat_id);

    @GET("endpoint.php?action=magazine_content")
    Call<List<MagazineContent>> getMagazinePDF(@Query("content_id") String pdf_id);

    @GET("bookmark.php")
    Call<Bookmark> doBookmarkArticle(@Query("content_id") String pdf_id, @Query("mac_id") String mac_id, @Query("access_token") String access_token);

    @GET("endpoint.php?action=bookmark")
    Call<List<NewsData>> getBookmarkList(@Query("mac_id") String mac_id);

    @GET("register.php")
    Call<UserData> doRegisterUser(@Query("mac_id") String mac_id, @Query("email") String email, @Query("contact") String contact, @Query("address") String address, @Query("city") String city, @Query("state") String state);

    @GET("getLatLong.php")
    Call<List<BranchInformation>> getNearbyBranch(@Query("lat") double lat, @Query("long")double log, @Query("limit") int limit);



}