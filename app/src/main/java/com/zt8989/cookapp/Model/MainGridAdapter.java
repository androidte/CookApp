package com.zt8989.cookapp.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/5/4.
 */
public class MainGridAdapter extends ArrayAdapter<CookClass> {
    private  int resourceId;
    private LayoutInflater mInflater;

    public MainGridAdapter(Context context, int resource, List<CookClass> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        get
        return super.getView(position, convertView, parent);
    }
}
