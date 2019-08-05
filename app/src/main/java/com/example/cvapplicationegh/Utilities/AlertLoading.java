package com.example.cvapplicationegh.Utilities;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;

import com.example.cvapplicationegh.R;

public class AlertLoading {

    public AlertDialog.Builder mBuilder;
    public AlertDialog dialog;
    public View viewAlert;

    public void makeDialog(Context mContext, Activity mAct)
    {
        mBuilder = new AlertDialog.Builder(mContext);
        viewAlert = LayoutInflater.from(mContext).inflate(R.layout.progress_bar, null);


        Display display = mAct.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int mWidth = size.x;
        final int mHeight = size.y;

        mBuilder.setView(viewAlert);
        dialog = mBuilder.create();

        if (dialog.getWindow() != null) {
            dialog.show();
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout((int) (mWidth * 0.8f), (int) (mHeight * 0.5)); //width,height

        }
    }

    public void dismissDialog() {
        dialog.dismiss();
    }
}