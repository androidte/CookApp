package com.zt8989.cookapp.Utils;

import com.zt8989.cookapp.Model.CookClass;
import com.zt8989.cookapp.Model.CookDetail;
import com.zt8989.cookapp.Model.CookItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;

/**
 * Created by Administrator on 2015/5/7.
 */
public class JSONHelper {
    public static CookClass getCookClassFromJson(JSONObject object) throws JSONException {
        CookClass cookClass=new CookClass(object.getLong("id"),object.getLong("cookclass"),
        object.getString("name"),0);
        return cookClass;
    }

    public static CookItem getCookItemFromJson(JSONObject object) throws JSONException {
        CookItem cookItem = new CookItem();
        cookItem.setId(object.getLong("id"));
        cookItem.setName(object.getString("name"));
        cookItem.setTag(object.getString("tag"));
        cookItem.setImg(object.getString("img"));
        cookItem.setCount(object.getInt("count"));
        cookItem.setFood(object.getString("food"));
        return cookItem;
    }

    public static CookDetail getCookDetailFromJson(JSONObject object,CookItem cookItem) throws JSONException {
        CookDetail cookDetail;
        if (cookItem != null) {
            cookDetail = new CookDetail(cookItem);
        } else {
            cookDetail = new CookDetail(getCookItemFromJson(object));
        }
        cookDetail.setMessage(object.getString("message"));
        return cookDetail;
    }
}
