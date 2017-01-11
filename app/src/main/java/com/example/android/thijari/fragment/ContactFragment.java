package com.example.android.thijari.fragment;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.thijari.R;
import com.example.android.thijari.rest.ThijariService;
import com.example.android.thijari.rest.listener.OnRetrofitResponse;
import com.example.android.thijari.rest.model.BranchInformation;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import java.util.List;

import okhttp3.ResponseBody;

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
    private ProgressBar loadingMapProgress;
    private LinearLayout informationLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
        branchName = (TextView)view.findViewById(R.id.branchName);
        branchTel = (TextView)view.findViewById(R.id.branchTel);
        branchFax = (TextView)view.findViewById(R.id.branchFax);
        branchEmail = (TextView)view.findViewById(R.id.branchEmail);
        branchOperationHour = (TextView)view.findViewById(R.id.branchOperationHour);
        loadingMapProgress = (ProgressBar)view.findViewById(R.id.loadingMapProgress);
        loadingMapProgress.setVisibility(View.GONE);
        informationLayout = (LinearLayout)view.findViewById(R.id.informationLayout);
        informationLayout.setVisibility(View.VISIBLE);
        mapFragment = (MarkerGoogleMapFragment)getChildFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.setUpdateLocationListener(this);
        return view;
    }

    private void initView(BranchInformation branch){
        branchName.setText(branch.getName());
        branchTel.setText(branch.getPhone());
        branchFax.setText(branch.getFax());
        branchEmail.setText(branch.getEmail());
        branchOperationHour.setText(branch.getOperation_hours());
        mapFragment.addMarker(branch);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MarkerGoogleMapFragment.REQUEST_CHECK_LOCATION && mapFragment!=null)
            mapFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void refreshLocation(Location location) {
        ThijariService.getInstance().getNearbyBranch(location.getLatitude(), location.getLongitude(), 1, new OnRetrofitResponse<List<BranchInformation>>() {
            @Override
            public void onResponse(List<BranchInformation> response) {
                if(response.size()>0)
                    initView(response.get(0));
                else
                    Toast.makeText(activity, getText(R.string.noBranch), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(ResponseBody errorBody) {
                Toast.makeText(activity, getText(R.string.noBranch), Toast.LENGTH_SHORT).show();
            }
        });


    }
}