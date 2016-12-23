package com.example.android.thijari.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.android.thijari.R;
import com.example.android.thijari.customTab.DemoCustomView02Adapter;
import com.example.android.thijari.customTab.DemoImitationLoopPagerAdapter;
import com.example.android.thijari.customTab.RecyclerTabLayout;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, ObservableScrollViewCallbacks {

    private ArrayList<Integer> mItems;
    private DemoImitationLoopPagerAdapter adapter;
    private MaterialViewPager viewPager;
    FrameLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("TESt");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mItems = new ArrayList<>();
        mItems.add(R.drawable.contact_small);
        mItems.add(R.drawable.magazine_small);
        mItems.add(R.drawable.news_small);
        mItems.add(R.drawable.promo_small);
        mItems.add(R.drawable.queue_small);
        mItems.add(R.drawable.trans_small);

        ArrayList<Integer> selected_icons = new ArrayList<>();
        selected_icons.add(R.drawable.contact);
        selected_icons.add(R.drawable.magazine);
        selected_icons.add(R.drawable.news);
        selected_icons.add(R.drawable.promo);
        selected_icons.add(R.drawable.queue);
        selected_icons.add(R.drawable.trans);

        adapter = new DemoImitationLoopPagerAdapter(getSupportFragmentManager());
        adapter.addAll(mItems);
        adapter.setSelectedIcons(selected_icons);

        root = (FrameLayout) findViewById(R.id.root);

        viewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        viewPager.getViewPager().setAdapter(adapter);
        viewPager.getViewPager().setCurrentItem(adapter.getCenterPosition(0));
        viewPager.getViewPager().addOnPageChangeListener(this);
        viewPager.getViewPager().setOffscreenPageLimit(0);
//        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(adapter.getCenterPosition(0));
//        viewPager.addOnPageChangeListener(this);
//        viewPager.setOffscreenPageLimit(0);


        RecyclerTabLayout recyclerTabLayout = (RecyclerTabLayout) findViewById(R.id.recycler_tab_layout);
        recyclerTabLayout.setUpWithAdapter(new DemoCustomView02Adapter(viewPager.getViewPager()));
        recyclerTabLayout.setPositionThreshold(0.5f);
//        viewPager.getPagerTitleStrip().setViewPager(viewPager.getViewPager());
//        recyclerTabLayout.setUpWithViewPager(viewPager.getViewPager());
//        viewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
//            @Override
//            public HeaderDesign getHeaderDesign(int page) {
////                switch (page) {
////                    case 0:
////                        return HeaderDesign.fromColorResAndUrl(
////                                R.color.green,
////                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
////                    case 1:
////                        return HeaderDesign.fromColorResAndUrl(
////                                R.color.blue,
////                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
////                    case 2:
////                        return HeaderDesign.fromColorResAndUrl(
////                                R.color.cyan,
////                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
////                    case 3:
////                        return HeaderDesign.fromColorResAndUrl(
////                                R.color.red,
////                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
////                }
//
//                //execute others actions if needed (ex : modify your header logo)
//
//                return HeaderDesign.fromColorResAndDrawable(R.color.green, );
//            }
//        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        boolean nearLeftEdge = (position <= mItems.size());
        boolean nearRightEdge = (position >= adapter.getCount() - mItems.size());
        if (nearLeftEdge || nearRightEdge) {
            viewPager.getViewPager().setCurrentItem(adapter.getCenterPosition(0), false);
        }
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
}
