package com.zt8989.cookapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.zt8989.cookapp.Model.CookDetail;
import com.zt8989.cookapp.Utils.HttpUtils;
import com.zt8989.cookapp.Utils.JSONHelper;
import com.zt8989.cookapp.Utils.TitleLayout;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/5/7.
 */
public class CookDetailActivity extends Activity {
    private TitleLayout titleLayout;
    private TextView contentView;

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
        setContentView(R.layout.cook_detail_layout);

        titleLayout = (TitleLayout) findViewById(R.id.detail_title);
        contentView = (TextView) findViewById(R.id.detail_content);
        Intent intent = getIntent();
        titleLayout.setText(intent.getStringExtra("title"));
        titleLayout.setBackgroundColor(0xFF0099CC);
        getCookDetail(intent.getLongExtra("classId",1));
    }

    private void getCookDetail(long id) {
        HttpUtils.get("show?id=" + id, null, new JsonHttpResponseHandler(){
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
                        updateContentView(cookDetail);
                    }

                } catch (JSONException e) {
                    Toast.makeText(CookDetailActivity.this,"get detail error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateContentView(CookDetail cookDetail) {
        contentView.setText(Html.fromHtml(cookDetail.getMessage()));
    }
}
