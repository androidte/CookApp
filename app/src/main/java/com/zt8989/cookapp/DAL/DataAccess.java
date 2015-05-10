package com.zt8989.cookapp.DAL;

import com.google.common.collect.Lists;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zt8989.cookapp.Model.CookClass;
import com.zt8989.cookapp.Model.CookSearchItem;
import com.zt8989.cookapp.R;
import com.zt8989.cookapp.Utils.HttpUtils;
import com.zt8989.cookapp.Utils.JSONHelper;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/21.
 */
public class DataAccess{
    /*
* 获得菜谱分类列表
* [{"name":"美容养颜","cookclass":0,"id":1},{"name":"减肥瘦身","cookclass":0,"id":2},
* {"name":"保健养生","cookclass":0,"id":3},{"name":"适宜人群","cookclass":0,"id":4},
* {"name":"餐食时节","cookclass":0,"id":5},{"name":"孕产哺乳","cookclass":0,"id":6},
* {"name":"女性养生","cookclass":0,"id":7},{"name":"男性养生","cookclass":0,"id":8},
* {"name":"心脏血管","cookclass":0,"id":9},{"name":"皮肤器官","cookclass":0,"id":10},
* {"name":"肠胃消化","cookclass":0,"id":11},{"name":"口腔呼吸","cookclass":0,"id":12},
* {"name":"肌肉神经","cookclass":0,"id":13},{"name":"癌症其他","cookclass":0,"id":14}]
* */
    public static List<CookClass> getCookClassList() {
        List<CookClass> list = Lists.newArrayList();
        list.add(new CookClass(1,"美容养颜", R.drawable.item1));
        list.add(new CookClass(2, "减肥瘦身",R.drawable.item2));
        list.add(new CookClass(3, "保健养生",R.drawable.item3));
        list.add(new CookClass(4, "适宜人群",R.drawable.item4));
        list.add(new CookClass(5, "餐食时节",R.drawable.item5));
        list.add(new CookClass(6, "孕产哺乳",R.drawable.item6));
        list.add(new CookClass(7, "女性养生",R.drawable.item7));
        list.add(new CookClass(8, "男性养生",R.drawable.item8));
        list.add(new CookClass(9, "心脏血管",R.drawable.item9));
        list.add(new CookClass(10, "皮肤器官",R.drawable.item10));
        list.add(new CookClass(11, "肠胃消化",R.drawable.item11));
        list.add(new CookClass(12,"口腔呼吸",R.drawable.item12));
        list.add(new CookClass(13, "肌肉神经",R.drawable.item13));
        list.add(new CookClass(14, "癌症其他",R.drawable.item14));
        return list;
    }

    /*
    * 获取下一级分类
    * */

 }
