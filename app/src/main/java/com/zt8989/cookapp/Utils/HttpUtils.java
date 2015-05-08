package com.zt8989.cookapp.Utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * Created by Administrator on 2015/4/21.
 */
public class HttpUtils {
    private static final String BASE_URL = "http://api.yi18.net/cook/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        client.setTimeout(5000);
    }
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        //Log.d("HttpUtils", BASE_URL + relativeUrl);
        return BASE_URL + relativeUrl;
    }
}
