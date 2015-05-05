package com.zt8989.cookapp.DAL;

import com.google.common.collect.Lists;

import java.util.List;
import com.zt8989.cookapp.Model.CookClass;
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
        list.add(new CookClass(1,0,"美容养颜"));
        list.add(new CookClass(2,0, "减肥瘦身"));
        list.add(new CookClass(3,0, "保健养生"));
        list.add(new CookClass(4,0, "适宜人群"));
        list.add(new CookClass(5,0, "餐食时节"));
        list.add(new CookClass(6,0, "孕产哺乳"));
        list.add(new CookClass(7,0, "女性养生"));
        list.add(new CookClass(8,0, "男性养生"));
        list.add(new CookClass(9,0, "心脏血管"));
        list.add(new CookClass(10,0, "皮肤器官"));
        list.add(new CookClass(11,0, "肠胃消化"));
        list.add(new CookClass(12,0,"口腔呼吸"));
        list.add(new CookClass(13,0, "肌肉神经"));
        list.add(new CookClass(14,0, "癌症其他"));
        return list;
    }

    /*
    * 获取下一级分类
    * */
    public static String getSubCookClassList(long id) {
        //TODO
        return "";
    }
 }
