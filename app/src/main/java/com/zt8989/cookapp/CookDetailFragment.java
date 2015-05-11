package com.zt8989.cookapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.zt8989.cookapp.Model.BaseCookItem;
import com.zt8989.cookapp.Model.CookDetail;
import com.zt8989.cookapp.Model.CookItem;
import com.zt8989.cookapp.Utils.HttpUtils;
import com.zt8989.cookapp.Utils.JSONHelper;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhou on 2015/5/10.
 */
public class CookDetailFragment extends android.support.v4.app.Fragment {
    public static final String ARG_COOK_ITEM = "CookITem";

    public static CookDetailFragment newInstance(BaseCookItem item) {
        CookDetailFragment fragment=new CookDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_COOK_ITEM,item);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.cook_detail_fragment, container, false);
        Bundle args=getArguments();
        BaseCookItem item = (BaseCookItem)args.getSerializable(ARG_COOK_ITEM);

        getCookDetail(item.getId());
        return rootview;
    }

    private void updateView(CookDetail cookDetail) {
        TextView contentView = (TextView)(getView().findViewById(R.id.detail_content));
        ImageView cookImg = (ImageView) (getView().findViewById(R.id.cook_img));
        contentView.setText(Html.fromHtml(cookDetail.getMessage()));
        Picasso.with(getActivity())
                .load(CookItem.BaseUrl + cookDetail.getImg())
                .into(cookImg);
    }

    private void getCookDetail(long id) {
        HttpUtils.get("show?id=" + id, null, new JsonHttpResponseHandler() {
            /**
             * Returns when request succeeds
             *
             * @param statusCode http response status line
             * @param headers    response headers if any
             * @param response   parsed response if any
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONObject object = response.getJSONObject("yi18");
                        CookDetail cookDetail = JSONHelper.getCookDetailFromJson(object, null);
                        updateView(cookDetail);
                    }

                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "get detail error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
