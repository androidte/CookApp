package com.zt8989.cookapp.Model;

/**
 * Created by Administrator on 2015/4/21.
 */
public class CookClass extends BaseCookItem{
    private long cookclass;
    private int imageId;

    public long getCookclass() {
        return cookclass;
    }

    public int getImageId() {
        return imageId;
    }

    public CookClass(long id,String name,int imageId) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return getName();
    }
}
