package com.example.android.thijari.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.thijari.R;
import com.example.android.thijari.adapter.MagazineViewAdapter;
import com.example.android.thijari.rest.ThijariService;
import com.example.android.thijari.rest.listener.OnRetrofitResponse;
import com.example.android.thijari.rest.model.Magazine;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class MagazineFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private MagazineViewAdapter mAdapter;

    public static MagazineFragment newInstance() {
        return new MagazineFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_magazine, container, false);
        setupProgressBar(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;

        layoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MagazineViewAdapter(activity);
        mRecyclerView.setNestedScrollingEnabled(true);
        //mAdapter = new RecyclerViewMaterialAdapter();
        mRecyclerView.setAdapter(mAdapter);

        ThijariService.getInstance().with(activity).getMainMagazine(new OnRetrofitResponse<List<Magazine>>() {
            @Override
            public void onResponse(List<Magazine> response) {
                isProgressBarShown(false);
                mAdapter.addAll(response);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(ResponseBody errorBody) {
            }
        });

        mAdapter.setOnItemClickListener(new MagazineViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Magazine magazine, int position) {
                passValueToActivity(magazine, getString(R.string.magazine));
            }
        });

    }
}
