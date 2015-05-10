package com.zt8989.cookapp.Utils;

import com.zt8989.cookapp.Model.CookClass;
import com.zt8989.cookapp.Model.CookDetail;
import com.zt8989.cookapp.Model.CookItem;
import com.zt8989.cookapp.Model.CookSearchItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/5/7.
 */
public class JSONHelper {
    public static CookClass getCookClassFromJson(JSONObject object) throws JSONException {
        CookClass item=new
                CookClass(object.getLong("id"), object.getString("name"),0);
        return item;
    }

    public static CookItem getCookItemFromJson(JSONObject object) throws JSONException {
        CookItem item = new CookItem();
        item.setId(object.getLong("id"));
        item.setName(object.getString("name"));
        item.setTag(object.getString("tag"));
        item.setImg(object.getString("img"));
        item.setCount(object.getInt("count"));
        item.setFood(object.getString("food"));
        return item;
    }

    public static CookDetail getCookDetailFromJson(JSONObject object,CookItem cookItem) throws JSONException {
        CookDetail item;
        if (cookItem != null) {
            item = new CookDetail(cookItem);
        } else {
            item = new CookDetail(getCookItemFromJson(object));
        }
        item.setMessage(object.getString("message"));
        return item;
    }

    public static CookSearchItem getCookSearchItemFromJson(JSONObject object) throws JSONException {
        CookSearchItem item=new CookSearchItem();
        item.setId(object.getLong("id"));
        item.setName(object.getString("name"));
        item.setImg(object.getString("img"));
        item.setContent(object.getString("content"));
        item.setDescription(object.getString("description"));
        item.setKeywrods(object.getString("keywords"));
        item.setType("type");
        return item;
    }
}
