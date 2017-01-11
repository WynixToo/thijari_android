package com.example.android.thijari.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.example.android.thijari.R;

/**
 * Created by Asus on 12/31/2016.
 */

public class QueueTutorialDialog extends Dialog {
    public QueueTutorialDialog(Context context, View.OnClickListener listener) {
        super(context, R.style.QueueDialogTheme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.dialog_queue_tutorial);
        if (listener != null)
            findViewById(R.id.selesaiBtn).setOnClickListener(listener);
    }

}
