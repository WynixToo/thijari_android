package com.example.android.thijari.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.thijari.R;
import com.example.android.thijari.adapter.MagazineListViewAdapter;


/**
 * Created by ykyee on 26/11/2015.
 */
public class TwoButtonDialog extends Dialog implements View.OnClickListener {

    private Button btnview, btndownload;
    private Context context;
    private onTowButtonDialogListener listener;
    private ProgressBar progressBar;
    private long mLastClickTime = 0;
    private TextView title, lbl1, lbl2;

    public TwoButtonDialog(Context context, onTowButtonDialogListener listener) {
        super(context);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_btn_dialog);

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        findView();
        setListener();
    }

    private void findView() {
        title = (TextView) findViewById(R.id.title);
        lbl1 = (TextView) findViewById(R.id.magazine_detail_title);
        lbl2 = (TextView) findViewById(R.id.magazine_detail_size);
        btndownload = (Button) findViewById(R.id.magazine_detail_download_btn);
        btnview = (Button) findViewById(R.id.magazine_detail_view_btn);
    }

    public void setCustomTitle(String titlelbl) {
        title.setText(titlelbl);
    }

    public void setLabel1(String label1) {
        lbl1.setText(label1);
    }

    public void setLabel2(String label2) {
        lbl2.setText(label2);
    }

    private void setListener() {
        btndownload.setOnClickListener(this);
        btnview.setOnClickListener(this);
    }

    public void toogleLoading(boolean isAppear) {
        if (isAppear)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.magazine_detail_view_btn:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                if (listener != null) {
                    listener.onBtn1Click("");
                }
                dismiss();
                break;
            case R.id.magazine_detail_download_btn:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                if (listener != null) {
                    listener.onBtn2Click("");
                }
                dismiss();
                break;
        }
    }

    public interface onTowButtonDialogListener {
        void onBtn1Click(String param);

        void onBtn2Click(String param);
    }
}
