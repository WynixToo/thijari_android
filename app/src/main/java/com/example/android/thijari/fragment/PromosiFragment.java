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
import com.example.android.thijari.adapter.TestRecyclerViewAdapter;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class PromosiFragment extends BaseFragment {

    public static PromosiFragment newInstance() {
        return new PromosiFragment();
    }

    ObservableScrollView mScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promo, container, false);

        return view;

    }
}
