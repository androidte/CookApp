package com.zt8989.cookapp.Model;

/**
 * Created by Administrator on 2015/5/7.
 */
public class CookDetail extends CookItem{

    public CookDetail(CookItem cookItem) {
        this.setId(cookItem.getId());
        this.setName(cookItem.getName());
        this.setTag(cookItem.getTag());
        this.setImg(cookItem.getImg());
        this.setCount(cookItem.getCount());
        this.setFood(cookItem.getImg());
    }

    public CookDetail() {
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
