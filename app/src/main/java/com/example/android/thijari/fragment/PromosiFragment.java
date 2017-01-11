package com.example.android.thijari.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.thijari.R;
import com.example.android.thijari.adapter.PromosiAdapter;
import com.example.android.thijari.rest.ThijariService;
import com.example.android.thijari.rest.listener.OnRetrofitResponse;
import com.example.android.thijari.rest.model.NewsData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class PromosiFragment extends BaseFragment {

    public static PromosiFragment newInstance() {
        return new PromosiFragment();
    }

    private RecyclerView mRecyclerView;
    private PromosiAdapter mAdapter;
    private List<NewsData> mContentItems = new ArrayList<>();
    private String PROMOSI = "36";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promo, container, false);
        setupProgressBar(view);
        RecyclerView.LayoutManager layoutManager;
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        System.out.println("%%%%% activity ="+activity);
        mAdapter = new PromosiAdapter(activity);
        //mAdapter = new RecyclerViewMaterialAdapter();
//        for (int i = 0; i < 10; ++i) {
//            mContentItems.add(new NewsData());
//        }
//        mAdapter.addAll(mContentItems);
        mRecyclerView.setAdapter(mAdapter);

        ThijariService.getInstance().with(activity).getNewsList(PROMOSI, new OnRetrofitResponse<List<NewsData>>() {
            @Override
            public void onResponse(List<NewsData> response) {
//                System.out.println(response);
                isProgressBarShown(false);
                mAdapter.addAll(response);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(ResponseBody errorBody) {

            }
        });

        mAdapter.setOnItemClickListener(new PromosiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsData newsData, int position) {
                passValueToActivity(newsData, getString(R.string.promo));
            }
        });


        return view;

    }
}
