package com.zt8989.cookapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.common.collect.Lists;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zt8989.cookapp.Model.CookClass;
import com.zt8989.cookapp.Model.CookItem;
import com.zt8989.cookapp.Utils.HttpUtils;
import com.zt8989.cookapp.Utils.JSONHelper;
import com.zt8989.cookapp.Utils.TitleLayout;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Administrator on 2015/5/7.
 */
public class CookListActivity extends Activity implements AdapterView.OnItemClickListener{
    public final static int PAGE = 1;
    public final static int LIMIT = 20;
    public final static String TYPE = "id";

    private TitleLayout titleLayout;
    private ListView cookItemListView;
    private List<CookItem> cookItemList;

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI, calling
     * {@link #managedQuery(android.net.Uri, String[], String, String[], String)} to retrieve
     * cursors for data being displayed, etc.
     * <p/>
     * <p>You can call {@link #finish} from within this function, in
     * which case onDestroy() will be immediately called without any of the rest
     * of the activity lifecycle ({@link #onStart}, {@link #onResume},
     * {@link #onPause}, etc) executing.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cook_list_layout);

        cookItemListView = (ListView) findViewById(R.id.cook_list_view);
        titleLayout = (TitleLayout) findViewById(R.id.cook_title);
        Intent intent = getIntent();
        titleLayout.setText(intent.getStringExtra("title"));
        titleLayout.setBackgroundColor(0xFF669900);
        cookItemListView.setOnItemClickListener(this);
        getCookList(intent.getLongExtra("classId", 1));
    }

    private void getCookList(Long id) {
        Map<String,String> params=new Hashtable<>();
        params.put("page", String.valueOf(PAGE));
        params.put("limit", String.valueOf(LIMIT));
        params.put("type", String.valueOf(TYPE));
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
                        cookItemList=new ArrayList<CookItem>();
                        try {
                            if (response.getBoolean("success")) {
                                JSONArray objects = response.getJSONArray("yi18");
                                for (int i = 0; i < objects.length(); i++) {
                                    CookItem item = JSONHelper.getCookItemFromJson(objects.getJSONObject(i));
                                    cookItemList.add(item);
                                }
                                updateCookListView(cookItemList);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(CookListActivity.this, "get sub list error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void updateCookListView(List<CookItem> cookItemList) {
        QuickAdapter<CookItem> adapter=new QuickAdapter<CookItem>(this,R.layout.cook_list_item,cookItemList) {
            @Override
            protected void convert(BaseAdapterHelper helper, CookItem item) {
                helper.setText(R.id.cook_name, item.getName())
                        .setText(R.id.cook_food, item.getFood())
                        .setImageUrl(R.id.cook_img,CookItem.BaseUrl+item.getImg());

            }
        };
        cookItemListView.setAdapter(adapter);

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
        CookItem cookItem = (CookItem) parent.getItemAtPosition(position);
        Intent intent=new Intent(CookListActivity.this,CookDetailActivity.class);
        intent.putExtra("title", cookItem.getName());
        intent.putExtra("classId", cookItem.getId());
        startActivity(intent);
    }
}
