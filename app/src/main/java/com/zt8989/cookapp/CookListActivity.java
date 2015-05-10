package com.zt8989.cookapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.common.collect.Lists;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.zt8989.cookapp.Model.CookItem;
import com.zt8989.cookapp.Utils.HttpUtils;
import com.zt8989.cookapp.Utils.JSONHelper;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/7.
 */
public class CookListActivity extends Activity implements AdapterView.OnItemClickListener{
    public final static int LIMIT = 20;

    private TitleLayout titleLayout;
    private ListView cookItemListView;
    private List<CookItem> list= Lists.newArrayList();
    private QuickAdapter<CookItem> adapter;
    private boolean isFirst = true; /*是否第一次获取*/
    private boolean lessThanLimit = false;
    private int currentPage = 0;
    private String type = "id";
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cook_list_layout);

        cookItemListView = (ListView) findViewById(R.id.cook_list_view);
        titleLayout = (TitleLayout) findViewById(R.id.cook_title);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        id = intent.getLongExtra("classId", 1);
        titleLayout.setText(intent.getStringExtra("title"));
        titleLayout.setBackgroundColor(0xFF669900);

        cookItemListView.setOnItemClickListener(this);
        adapter=new QuickAdapter<CookItem>(this,R.layout.cook_list_item,null) {
            @Override
            protected void convert(BaseAdapterHelper helper, CookItem item) {
                RequestCreator builer= Picasso.with(CookListActivity.this)
                        .load(CookItem.BaseUrl+item.getImg())
                        .resize(50,50).centerCrop().onlyScaleDown();

                helper.setText(R.id.cook_name, item.getName())
                        .setText(R.id.cook_food, item.getFood())
                        .setImageBuilder(R.id.cook_img,builer);

            }
        };
        adapter.showIndeterminateProgress(true);
        cookItemListView.setAdapter(adapter);
        cookItemListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isFirst || lessThanLimit) {
                    return;
                }
                if (view.getLastVisiblePosition() == totalItemCount - 1) {
                    adapter.showIndeterminateProgress(true);
                    getCookList();
                }
            }
        });
        getCookList();
    }


    private void getCookList() {
        Map<String,String> params=new Hashtable<>();
        params.put("page", String.valueOf(++currentPage));
        params.put("limit", String.valueOf(LIMIT));
        params.put("type", String.valueOf(type));
        params.put("id", String.valueOf(id));

        HttpUtils.get("list", new RequestParams(params), new JsonHttpResponseHandler() {
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
                                JSONArray objects = response.getJSONArray("yi18");
                                for (int i = 0; i < objects.length(); i++) {
                                    CookItem item = JSONHelper.getCookItemFromJson(objects.getJSONObject(i));
                                    list.add(item);
                                }
                                updateCookListView(list);
                            }
                        } catch (JSONException e) {
                            showToast("parse error");
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        showToast("network problem!");
                    }
                }


        );
    }

    private void updateCookListView(List<CookItem> list) {
        adapter.showIndeterminateProgress(false);

        if (isFirst) {
            isFirst = false;
        }

        if(list.size()<LIMIT) {
            lessThanLimit = true;
        }

        if (list.size() < 0) {
            showToast("empty list");
            return;
        }

        adapter.addAll(list);
    }

    private void showToast(String text) {
        Toast.makeText(CookListActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(CookListActivity.this,CookDetailActivity.class);
        Bundle args=new Bundle();
        args.putSerializable("List",(Serializable) list);
        args.putInt("position", position);
        intent.putExtras(args);
        startActivity(intent);
    }
}
