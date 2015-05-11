package com.zt8989.cookapp.Model;

import java.io.Serializable;

/**
 * Created by zhou on 2015/5/10.
 */
public class BaseCookItem  implements Serializable {
    protected long id;
    protected String name;
    protected String img;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
