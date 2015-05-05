package com.zt8989.cookapp.Model;

/**
 * Created by Administrator on 2015/4/21.
 */
public class CookClass {
    private long id;
    private long cookclass;
    private String name;

    public long getCookclass() {
        return cookclass;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CookClass(long id, long cookclass, String name) {
        this.id = id;
        this.cookclass = cookclass;
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
