package com.zt8989.cookapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zt8989.cookapp.R;

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
