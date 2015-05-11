package com.zt8989.cookapp.DAL;

import android.content.Context;

import com.joanzapata.android.QuickAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/5/11.
 */
public abstract class MyQuickAdapter<T> extends QuickAdapter<T> {
    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public MyQuickAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public List<T> getData() {
        return this.data;
    }
}
