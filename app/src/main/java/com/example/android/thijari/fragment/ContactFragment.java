package com.example.android.thijari.fragment;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.thijari.R;
import com.example.android.thijari.rest.model.BranchInformation;
import com.example.android.thijari.rest.model.BranchLocation;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class ContactFragment extends BaseFragment implements MarkerGoogleMapFragment.OnUpdateLocationListener{


    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    ObservableScrollView mScrollView;
    private TextView branchName, branchTel, branchFax, branchEmail,branchOperationHour;
    private MarkerGoogleMapFragment mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
        branchName = (TextView)view.findViewById(R.id.branchName);
        branchTel = (TextView)view.findViewById(R.id.branchTel);
        branchFax = (TextView)view.findViewById(R.id.branchFax);
        branchEmail = (TextView)view.findViewById(R.id.branchEmail);
        branchOperationHour = (TextView)view.findViewById(R.id.branchOperationHour);
        mapFragment = (MarkerGoogleMapFragment)getChildFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.setUpdateLocationListener(this);
        initView();
        return view;
    }

    BranchInformation information = new BranchInformation();
    private void initView(){
        information.setEmail("th-info@lth.gov.my");
        information.setFax("03 - 7728 4959");
        information.setName("TABUNG HAJI CONTACT CENTER (THCC)");
        information.setTel("03 - 6027 1919");
        information.setWaktuOperasi("8:00 pagi - 5:30 petang");

        BranchLocation location = new BranchLocation();
        location.setLongitude(101.617269);
        location.setLatitude(3.077326);
        location.setTitle("Masjid Al - Islamiah Kg Lindungan");
        location.setAddress("Kampung Lindungan, Sungai Way, Selangor, Jalan PJS 6/4, Pjs 6, 46000 Petaling Jaya, Selangor, Malaysia");
        information.setLocation(location);

        branchName.setText(information.getName());
        branchOperationHour.setText(information.getWaktuOperasi());
        branchEmail.setText(information.getEmail());
        branchFax.setText(information.getFax());
        branchTel.setText(information.getTel());

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MarkerGoogleMapFragment.REQUEST_CHECK_LOCATION && mapFragment!=null)
            mapFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void refreshLocation(Location location) {
        mapFragment.addMarker(information.getLocation());
    }
}
