package com.zt8989.cookapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.zt8989.cookapp.Model.CookClass;
import com.zt8989.cookapp.Utils.HttpUtils;
import com.zt8989.cookapp.Utils.JSONHelper;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/6.
 */
public class SubListActivity extends Activity implements AdapterView.OnItemClickListener{
    private ListView subListView;
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
        setContentView(R.layout.sub_list_layout);

        subListView = (ListView) findViewById(R.id.sub_list_view);
        Intent intent = getIntent();
        TitleLayout titleLayout = (TitleLayout) findViewById(R.id.sub_title);
        titleLayout.setText(intent.getStringExtra("title"));
        titleLayout.setBackgroundColor(0xFFFF8800);

        getSubCookClass(intent.getLongExtra("classId", 1));
    }

    private void getSubCookClass(long id) {
        HttpUtils.get("cookclass?id=" + id, null, new JsonHttpResponseHandler(){
                    /**
                     * Returns when request succeeds
                     *
                     * @param statusCode http response status line
                     * @param headers    response headers if any
                     * @param response   parsed response if any
                     */
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        List<CookClass> cookClassList=new ArrayList<CookClass>();
                        try {
                            if (response.getBoolean("success")) {
                                JSONArray subList = response.getJSONArray("yi18");
                                for (int i = 0; i < subList.length(); i++) {
                                    CookClass cookClass = JSONHelper.getCookClassFromJson(subList.getJSONObject(i));
                                    cookClassList.add(cookClass);
                                }
                                updateListView(cookClassList);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(SubListActivity.this, "get sub list error",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }

    private void updateListView(List<CookClass> cookClassList) {
        QuickAdapter<CookClass> adapter=new QuickAdapter<CookClass>(this,R.layout.sub_list_item,cookClassList) {
            @Override
            protected void convert(BaseAdapterHelper helper, CookClass item) {
                helper.setText(R.id.sub_list_name, item.getName());
            }
        };
        subListView.setAdapter(adapter);
        subListView.setOnItemClickListener(this);
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
        CookClass cookClass = (CookClass) parent.getItemAtPosition(position);
        Intent intent=new Intent(SubListActivity.this,CookListActivity.class);
        intent.putExtra("title", cookClass.getName());
        intent.putExtra("classId", cookClass.getId());
        startActivity(intent);
    }
}
