package com.example.android.thijari.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.android.thijari.R;
import com.example.android.thijari.adapter.MagazineViewAdapter;
import com.example.android.thijari.adapter.MainMenuViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ANDROID on 10/01/2017.
 */

public class MainMenuActivity extends BaseActivity {
    @Override
    protected int getLayoutResource() {
        return R.layout.mainmenu_activity;
    }

    @Override
    protected boolean isMainActivity() {
        return false;
    }

    @Override
    public void onFragmentCallback(Object Data, String from) {

    }

    private ViewFlipper viewFlipper;
    private RecyclerView mRecyclerView;
    private MainMenuViewAdapter mAdapter;
    private RelativeLayout penbtn, hajibtn, myqbtn;
//    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        penbtn = (RelativeLayout) findViewById(R.id.penbtn);
        hajibtn = (RelativeLayout) findViewById(R.id.hajibtn);
        myqbtn = (RelativeLayout) findViewById(R.id.myqbtn);


        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ban_berita_terkini);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewFlipper.addView(imageView);

        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.ban_majalah);
        imageView1.setAdjustViewBounds(true);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewFlipper.addView(imageView1);

        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.ban_promosi);
        imageView2.setAdjustViewBounds(true);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewFlipper.addView(imageView2);

        mRecyclerView = (RecyclerView) findViewById(R.id.right_panel);
        RecyclerView.LayoutManager layoutManager;

        layoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MainMenuViewAdapter(this);
        mRecyclerView.setNestedScrollingEnabled(true);
        //mAdapter = new RecyclerViewMaterialAdapter();
        initMenuArray();

//        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(final View view, final MotionEvent event) {
//                detector.onTouchEvent(event);
//                return true;
//            }
//        });

    }

    public void initMenuArray() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        MenuItem menuItem = new MenuItem();
        menuItem.setMenuTitle(getString(R.string.news));
        menuItem.setMenuDrawable(R.drawable.icon_berita_terkini);
        menuItems.add(menuItem);

        MenuItem promo = new MenuItem();
        promo.setMenuTitle(getString(R.string.promo));
        promo.setMenuDrawable(R.drawable.icon_promosi);
        menuItems.add(promo);


        MenuItem magazine = new MenuItem();
        magazine.setMenuTitle(getString(R.string.magazine));
        magazine.setMenuDrawable(R.drawable.icon_majalah);
        menuItems.add(magazine);


        MenuItem contact = new MenuItem();
        contact.setMenuTitle(getString(R.string.contact));
        contact.setMenuDrawable(R.drawable.icon_lokasi);
        menuItems.add(contact);


        MenuItem change_language = new MenuItem();
        change_language.setMenuTitle(getString(R.string.change_language));
        change_language.setMenuDrawable(R.drawable.icon_tukar_bahasa);
        menuItems.add(change_language);
        mAdapter.addAll(menuItems);
        mRecyclerView.setAdapter(mAdapter);

        setListener();
    }

    public void setListener() {
        myqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPageView("4", "CHECK_IN");
            }
        });
        penbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPageView("5", "PENDEPOSIT");
            }
        });
        hajibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPageView("5", "HAJI");
            }
        });
        mAdapter.setOnItemClickListener(new MainMenuViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MenuItem menuItem, int position) {
                if (menuItem.getMenuTitle().equals(getString(R.string.contact))) {
                    openMainPageView("0");
                } else if (menuItem.getMenuTitle().equals(getString(R.string.magazine))) {
                    openMainPageView("1");
                } else if (menuItem.getMenuTitle().equals(getString(R.string.news))) {
                    openMainPageView("2");
                } else if (menuItem.getMenuTitle().equals(getString(R.string.promo))) {
                    openMainPageView("3");
                }
            }
        });
    }

    private void openMainPageView(String position) {
        openMainPageView(position, "");
    }

    private void openMainPageView(String position, String gotoType) {
        Intent myIntent = new Intent(this, MainActivity2.class);
        myIntent.putExtra("POSITION", position);
        myIntent.putExtra("GOTOTYPE", gotoType);
        this.startActivity(myIntent);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
