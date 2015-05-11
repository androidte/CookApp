package com.zt8989.cookapp;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/5/6.
 */
public class TitleLayout extends LinearLayout {
    private  TextView titleTxt;
    private LinearLayout linearLayout;

    public void setText(CharSequence mText) {
        titleTxt.setText(mText);
    }

    public void setBackgroundColor(int color) {
        linearLayout.setBackgroundColor(color);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        ImageView backBtn = (ImageView) findViewById(R.id.back_btn);
        titleTxt = (TextView) findViewById(R.id.title_txt);
        linearLayout = (LinearLayout) findViewById(R.id.title_layout);

        backBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }
}
