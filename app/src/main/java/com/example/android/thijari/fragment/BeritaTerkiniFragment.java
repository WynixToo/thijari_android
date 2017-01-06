package com.example.android.thijari.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.thijari.R;
import com.example.android.thijari.adapter.TestRecyclerViewAdapter;
import com.example.android.thijari.rest.ThijariService;
import com.example.android.thijari.rest.listener.OnRetrofitResponse;
import com.example.android.thijari.rest.model.Feed;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class BeritaTerkiniFragment extends BaseFragment {

    static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 10;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Object> mContentItems = new ArrayList<>();

    public static BeritaTerkiniFragment newInstance() {
        return new BeritaTerkiniFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    ObservableScrollView mScrollView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
//        setScrollView(mScrollView);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;

        if (GRID_LAYOUT) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getActivity());
        }
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setNestedScrollingEnabled(true);

        //Use this now
        mAdapter = new TestRecyclerViewAdapter(activity);

        //mAdapter = new RecyclerViewMaterialAdapter();
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setScrollViewCallbacks(this);
        ThijariService.getInstance().with(activity).getMainPageFeed(new OnRetrofitResponse<List<Feed>>() {
            @Override
            public void onResponse(List<Feed> response) {

            }

            @Override
            public void onFailure(ResponseBody errorBody) {

            }
        });

        {
            for (int i = 0; i < ITEM_COUNT; ++i) {
                mContentItems.add(new Object());
            }
            mAdapter.notifyDataSetChanged();
        }
    }

}
