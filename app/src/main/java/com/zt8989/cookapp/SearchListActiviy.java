package com.zt8989.cookapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Lists;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.zt8989.cookapp.DAL.MyQuickAdapter;
import com.zt8989.cookapp.Model.CookItem;
import com.zt8989.cookapp.Model.CookSearchItem;
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
 * Created by zhou on 2015/5/10.
 */
public class SearchListActiviy extends Activity implements AdapterView.OnItemClickListener {
    public final static String ACTION_SEARCH = "com.zt8989.CookApp.ACTION_SEARCH";
    public final static String CATEGORY_SEARCH = "com.zt8989.CookApp.SEARCH";
    public final static String CATEGORY_RESULT = "com.zt8989.CookApp.RESULT";
    public final static String KEYWORD = "keyword";
    public final static int LIMIT = 20;

    private TitleLayout titleLayout;
    private ListView listView;
    private MyQuickAdapter<CookSearchItem> adapter;
    private boolean isFirst = true;
    private boolean lessThanLimit = false;
    private int page = 0;

    //定义获取状态
    private final static int FIRSTLOADING = 0x00; //第一次加载
    private final static int LOADING = 0x01; //第二次以后加载
    private final static int COMPLETE = 0x02; //加载完成
    private final static int NONE=0x03; //没有更多可以加载
    private int state = FIRSTLOADING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.result_layout);

        titleLayout = (TitleLayout) findViewById(R.id.result_title);
        listView=(ListView)findViewById(R.id.result_list);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent.hasCategory(CATEGORY_RESULT)) {
            final String keyword = intent.getStringExtra(KEYWORD);
            titleLayout.setText(keyword);
            adapter=new MyQuickAdapter<CookSearchItem>(this,R.layout.cook_list_item) {
                @Override
                protected void convert(BaseAdapterHelper helper, CookSearchItem item) {
                    RequestCreator builer= Picasso.with(SearchListActiviy.this)
                            .load(CookItem.BaseUrl + item.getImg())
                            .resize(50, 50).centerCrop().onlyScaleDown();

                    helper.setImageBuilder(R.id.cook_img, builer);
                    TextView view = helper.getView(R.id.cook_name);
                    view.setText(Html.fromHtml(item.getName()));
                }
            };
            adapter.showIndeterminateProgress(true);
            listView.setAdapter(adapter);
            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (state == COMPLETE && view.getLastVisiblePosition() == totalItemCount - 1) {
                        adapter.showIndeterminateProgress(true);
                        getCookSearchItems(keyword);
                    }
                }
            });
            listView.setOnItemClickListener(this);
            getCookSearchItems(keyword);
        }
    }

    public void getCookSearchItems(String keyword){
        state=LOADING;
        //TODO
        Map<String, String> params = new Hashtable<>();
        params.put("page", String.valueOf(++page));
        params.put("limit", String.valueOf(LIMIT));
        params.put(KEYWORD, keyword);

        HttpUtils.post("search", new RequestParams(params), new JsonHttpResponseHandler() {
                    /**
                     * Returns when request succeeds
                     *
                     * @param statusCode http response status line
                     * @param headers    response headers if any
                     * @param response   parsed response if any
                     */
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        List<CookSearchItem> list = Lists.newArrayList();
                        try {
                            if (response.getBoolean("success")) {
                                JSONArray objects = response.getJSONArray("yi18");
                                for (int i = 0; i < objects.length(); i++) {
                                    CookSearchItem item = JSONHelper.getCookSearchItemFromJson(objects.getJSONObject(i));
                                    list.add(item);
                                }
                                updateListView(list);
                            }
                        } catch (JSONException e) {
                            showToast("parse error");
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        showToast("network problem!");
                        state = COMPLETE;
                    }
                }
        );
    }

    private void updateListView(List<CookSearchItem> list) {
        adapter.showIndeterminateProgress(false);

        if (list.size() < 0) {
            showToast("empty list");
            return;
        }

        if (list.size() < 20) {
            state = NONE;
        } else {
            state = COMPLETE;
        }

        adapter.addAll(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(SearchListActiviy.this,CookDetailActivity.class);
        Bundle args=new Bundle();
        args.putSerializable("List",(Serializable)adapter.getData());
        args.putInt("position", position);
        intent.putExtras(args);
        startActivity(intent);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
