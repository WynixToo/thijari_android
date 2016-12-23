package com.example.android.thijari.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.android.thijari.R;
import com.example.android.thijari.customTab.DemoCustomView02Adapter;
import com.example.android.thijari.customTab.DemoImitationLoopPagerAdapter;
import com.example.android.thijari.customTab.RecyclerTabLayout;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.wynixtoo.thijariapilib.model.Magazine;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity2 extends BaseActivity implements ViewPager.OnPageChangeListener, ObservableScrollViewCallbacks {

    private ArrayList<Integer> mItems;
    private DemoImitationLoopPagerAdapter adapter;
    private ViewPager viewPager;
    CoordinatorLayout root;
    private ImageView headerImg;
    private ArrayList<Integer> selected_icons;
    private ArrayList<Integer> banner_imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        useToolbar(false, "");

        initMenu();

        headerImg = (ImageView) findViewById(R.id.htab_header);

        adapter = new DemoImitationLoopPagerAdapter(getSupportFragmentManager());
        adapter.addAll(mItems);
        adapter.setSelectedIcons(selected_icons);

        root = (CoordinatorLayout) findViewById(R.id.root);

        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(adapter.getCenterPosition(0));
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(0);


        RecyclerTabLayout recyclerTabLayout = (RecyclerTabLayout) findViewById(R.id.recycler_tab_layout);
        recyclerTabLayout.setUpWithAdapter(new DemoCustomView02Adapter(viewPager));
        recyclerTabLayout.setPositionThreshold(0.5f);
    }

    public void initMenu() {
        mItems = new ArrayList<>();
        mItems.add(R.drawable.contact_small);
        mItems.add(R.drawable.magazine_small);
        mItems.add(R.drawable.news_small);
        mItems.add(R.drawable.promo_small);
        mItems.add(R.drawable.queue_small);
        mItems.add(R.drawable.trans_small);

        selected_icons = new ArrayList<>();
        selected_icons.add(R.drawable.contact);
        selected_icons.add(R.drawable.magazine);
        selected_icons.add(R.drawable.news);
        selected_icons.add(R.drawable.promo);
        selected_icons.add(R.drawable.queue);
        selected_icons.add(R.drawable.trans);

        banner_imgs = new ArrayList<>();
        banner_imgs.add(R.drawable.mag_banner);
        banner_imgs.add(R.drawable.mag_banner);
        banner_imgs.add(R.drawable.news_banner);
        banner_imgs.add(R.drawable.promo_banner);
        banner_imgs.add(R.drawable.queue_banner);
        banner_imgs.add(R.drawable.trans_banner);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main_te;
    }

    @Override
    protected boolean isMainActivity() {
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        boolean nearLeftEdge = (position <= mItems.size());
        boolean nearRightEdge = (position >= adapter.getCount() - mItems.size());
        if (nearLeftEdge || nearRightEdge) {
            viewPager.setCurrentItem(adapter.getCenterPosition(0), false);
        }
        headerImg.setImageResource(banner_imgs.get(position % banner_imgs.size()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    @Override
    public void onFragmentCallback(Object Data) {
        if (Data instanceof Magazine) {
            Magazine magazine = (Magazine) Data;
            Intent myIntent = new Intent(this, ShowAllMagazineActivity.class);
            myIntent.putExtra("CATEGORY", magazine.getCategoryString());
            myIntent.putExtra("CATEGORYID", magazine.getCategoryID());
            this.startActivity(myIntent);
        }
    }
}
