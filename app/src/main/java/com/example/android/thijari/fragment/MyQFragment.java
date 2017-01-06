package com.example.android.thijari.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.thijari.R;
import com.example.android.thijari.dialog.QueueTutorialDialog;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class MyQFragment extends BaseFragment implements View.OnClickListener {

    private View overlayView;
    private TextView step1Number,step2Number,step1Description,step2Description,closeTutorialTxt;

    public static MyQFragment newInstance() {
        return new MyQFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queue, container, false);
        view.findViewById(R.id.queueSelectBranch).setOnClickListener(this);
        view.findViewById(R.id.queueCheckIn).setOnClickListener(this);
        view.findViewById(R.id.queueTutorial).setOnClickListener(this);
        step1Number = (TextView)view.findViewById(R.id.step1);
        step1Description = (TextView)view.findViewById(R.id.step1Description);
        step2Number = (TextView)view.findViewById(R.id.step2);
        step2Description = (TextView)view.findViewById(R.id.step2Description);
        overlayView = view.findViewById(R.id.overlayView);
        closeTutorialTxt = (TextView)view.findViewById(R.id.closeTutorialTxt);
        closeTutorialTxt.setOnClickListener(this);
        return view;

    }

    private QueueTutorialDialog dialog = null;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.queueSelectBranch:
                Toast.makeText(activity, "Select Tabung Haji Branch clicked.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.queueCheckIn:
                Toast.makeText(activity, "Check in Tabung Haji clicked.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.queueTutorial:
                dialog = new QueueTutorialDialog(activity, this);
                dialog.show();
                break;

            case R.id.selesaiBtn:
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                break;

            case R.id.closeTutorialTxt:
                isShowingTutorial(false);
                break;
        }
    }

    private void isShowingTutorial(boolean isShow){
        if(isShow){
            step1Number.setVisibility(View.VISIBLE);
            step1Description.setVisibility(View.VISIBLE);
            step2Number.setVisibility(View.VISIBLE);
            step2Description.setVisibility(View.VISIBLE);
            closeTutorialTxt.setVisibility(View.VISIBLE);
//            overlayView.setVisibility(View.VISIBLE);
        }else{

            step1Number.setVisibility(View.GONE);
            step1Description.setVisibility(View.GONE);
            step2Number.setVisibility(View.GONE);
            step2Description.setVisibility(View.GONE);
            closeTutorialTxt.setVisibility(View.GONE);
//            overlayView.setVisibility(View.GONE);
        }
    }

}
