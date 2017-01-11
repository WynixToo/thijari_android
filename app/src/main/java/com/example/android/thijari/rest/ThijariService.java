package com.example.android.thijari.rest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.android.thijari.R;
import com.example.android.thijari.rest.listener.OnRetrofitResponse;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Asus on 12/10/2016.
 */

public class ThijariService {

    private static ThijariService _instance;

    private final String baseUrl = "http://bclnglobal.com/newsappdev/";
    private final String branchUrl = "http://thijari.haqqi.com.my/";
    private ThijariAPI api;
    private ThijariAPI nextPageApi;
    private ThijariAPI branchApi;
    private Context context;
    private Dialog loadingDialog;
    private String connectionErrorMsg;

    public static ThijariService getInstance() {
        return _instance = _instance == null ? new ThijariService() : _instance;
    }


    private ThijariService() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(new GsonTypeAdapterFactory());
        Gson gson = builder.create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).build();
        api = retrofit.create(ThijariAPI.class);
        nextPageApi = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(ThijariAPI.class);
        branchApi = new Retrofit.Builder().baseUrl(branchUrl).addConverterFactory(GsonConverterFactory.create()).build().create(ThijariAPI.class);
    }


    public ThijariService with(Context context) {
        this.context = context;
        if (TextUtils.isEmpty(connectionErrorMsg))
            connectionErrorMsg = context.getString(R.string.connectionError);
        return _instance;
    }

    private ColorDrawable dialogBackground;

    public ThijariService isLoading() {
        if (dialogBackground == null)
            dialogBackground = new ColorDrawable(Color.TRANSPARENT);
        loadingDialog = new Dialog(context);
//        loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loadingDialog.getWindow().setBackgroundDrawable(dialogBackground);
//        loadingDialog.setContentView(R.layout.thijari_loading_dialog);
        loadingDialog.show();
        return _instance;
    }

    public void loginWithMacId(String macId, final OnRetrofitResponse<UserData> callback) {
        Call<UserData> call = api.getTokenByLogin(macId);
        call.enqueue(new RetrofitResponseHandler<UserData>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

    public void getMainMagazine(final OnRetrofitResponse<List<Magazine>> callback) {
        Call<List<Magazine>> call = api.getMainMagazine();
        call.enqueue(new RetrofitResponseHandler<List<Magazine>>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

//    private void handleError(OnRetrofitResponse callback, String errorMessage){
//        if (context != null)
//            Toast.makeText(context, context.getString(R.string.connectionError), Toast.LENGTH_SHORT).show();
//        callback.onFailure(null);
//    }

    public void getMainPageFeed(final OnRetrofitResponse<List<Feed>> callback) {
        Call<List<Feed>> call = api.getMainPageFeed();
        call.enqueue(new RetrofitResponseHandler<List<Feed>>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

    public void doRegisterUser(String macId, String email, String contact, String address, String city, String state, final OnRetrofitResponse<UserData> callback) {
        Call<UserData> call = api.doRegisterUser(macId, email, contact, address, city, state);
        call.enqueue(new RetrofitResponseHandler<UserData>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

    public void getMagazineList(String categoryId, final OnRetrofitResponse<List<MagazineListData>> callback) {
        Call<List<MagazineListData>> call = api.getMagazineList(categoryId);
        call.enqueue(new RetrofitResponseHandler<List<MagazineListData>>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

    public void getMagazinePDF(String contentId, final OnRetrofitResponse<List<MagazineContent>> callback) {
        Call<List<MagazineContent>> call = api.getMagazinePDF(contentId);
        call.enqueue(new RetrofitResponseHandler<List<MagazineContent>>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

    public void getNewsList(String categoryId, final OnRetrofitResponse<List<NewsData>> callback) {
        Call<List<NewsData>> call = api.getNewsList(categoryId);
        call.enqueue(new RetrofitResponseHandler<List<NewsData>>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

    public void getNextOrPreviousNewsList(String url, final OnRetrofitResponse<NextPageObject> callback) {
        Uri uri = Uri.parse(url);
        Set<String> para = uri.getQueryParameterNames();
        Map parameter = new HashMap<String, String>();
        for (String paraName : para) {
            parameter.put(paraName, uri.getQueryParameter(paraName));
        }

        Call<NextPageObject> call = nextPageApi.getNextOrPreviousNewsList(parameter);
        call.enqueue(new RetrofitResponseHandler<NextPageObject>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

    public void getSubFeedList(String categoryID, final OnRetrofitResponse<List<SubCategoryData>> callback) {
        Call<List<SubCategoryData>> call = api.getSubFeedList(categoryID);
        call.enqueue(new RetrofitResponseHandler<List<SubCategoryData>>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

    public void getFullContent(String contentId, final OnRetrofitResponse<List<FullNewsContentData>> callback) {
        Call<List<FullNewsContentData>> call = api.getFullContent(contentId);
        call.enqueue(new RetrofitResponseHandler<List<FullNewsContentData>>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);

            }
        });
    }

    public void bookmarkPage(String mac_id, String contentId, String accessToken, final OnRetrofitResponse<Bookmark> callback) {
        Call<Bookmark> call = api.doBookmarkArticle(contentId, mac_id, accessToken);
        call.enqueue(new RetrofitResponseHandler<Bookmark>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

    public void getBookmarkedList(String mac_id, final OnRetrofitResponse<List<NewsData>> callback) {
        Call<List<NewsData>> call = api.getBookmarkList(mac_id);
        call.enqueue(new RetrofitResponseHandler<List<NewsData>>(loadingDialog, callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }

    public void getNearbyBranch(double lat, double log, int limit, final OnRetrofitResponse<List<BranchInformation>> callback){
        System.out.println("%%%%% limit = "+limit);
        Call<List<BranchInformation>> call = branchApi.getNearbyBranch(lat, log, limit);
        call.enqueue(new RetrofitResponseHandler<List<BranchInformation>>(loadingDialog,callback) {
            @Override
            void handleError(String errorMessage) {
                if (context != null)
                    Toast.makeText(context, connectionErrorMsg, Toast.LENGTH_SHORT).show();
                callback.onFailure(null);
            }
        });
    }
}