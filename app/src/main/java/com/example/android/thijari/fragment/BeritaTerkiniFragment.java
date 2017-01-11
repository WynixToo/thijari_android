package com.example.android.thijari.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.android.thijari.R;
import com.example.android.thijari.adapter.GridMenuViewAdapter;
import com.example.android.thijari.adapter.TestRecyclerViewAdapter;
import com.example.android.thijari.rest.ThijariService;
import com.example.android.thijari.rest.listener.OnRetrofitResponse;
import com.example.android.thijari.rest.model.NewsData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class BeritaTerkiniFragment extends BaseFragment {

    static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 10;
    private RecyclerView mRecyclerView, mGridRecyclerView;
    private TestRecyclerViewAdapter mAdapter;
    private List<NewsData> mContentItems = new ArrayList<>();
    private List<Integer> mGridMenu = new ArrayList<>();
    private GridMenuViewAdapter gridMenuViewAdapter;
    private String HIGHLIGHT = "5";

    public static BeritaTerkiniFragment newInstance() {
        return new BeritaTerkiniFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        setupProgressBar(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
//        setScrollView(mScrollView);
        RecyclerView.LayoutManager layoutManager;
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setNestedScrollingEnabled(false);


//        RecyclerView.LayoutManager layoutManager2;
//        mGridRecyclerView = (RecyclerView) view.findViewById(R.id.gridmenu);
//        layoutManager2 = new GridLayoutManager(getActivity(), 3);
//        mGridRecyclerView.setLayoutManager(layoutManager2);
//        mGridRecyclerView.setHasFixedSize(true);
////        mGridRecyclerView.setNestedScrollingEnabled(true);
//        initGridMenu();
//        mGridRecyclerView.setAdapter(gridMenuViewAdapter);

//        if (GRID_LAYOUT) {
//            layoutManager = new GridLayoutManager(getActivity(), 2);
//        } else {


        //Use this now
        mAdapter = new TestRecyclerViewAdapter(activity);
        //mAdapter = new RecyclerViewMaterialAdapter();
//        for (int i = 0; i < ITEM_COUNT; ++i) {
//            mContentItems.add(new Object());
//        }
//        mAdapter.addAll(mContentItems);
        mRecyclerView.setAdapter(mAdapter);

//        mRecyclerView.setScrollViewCallbacks(this);
        ThijariService.getInstance().with(activity).getNewsList(HIGHLIGHT, new OnRetrofitResponse<List<NewsData>>() {
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

        mAdapter.setOnItemClickListener(new TestRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsData newsData, int position) {
                passValueToActivity(newsData, getString(R.string.news));
            }
        });
    }

    private void initGridMenu() {
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_bookmark);
        mGridMenu.add(R.drawable.cat_banking);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_family);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        mGridMenu.add(R.drawable.cat_auto);
        gridMenuViewAdapter = new GridMenuViewAdapter(activity);
        gridMenuViewAdapter.addAll(mGridMenu);

    }

}
