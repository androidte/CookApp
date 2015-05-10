package com.zt8989.cookapp.Model;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Administrator on 2015/5/7.
 */
public class CookItem extends BaseCookItem{
    private String tag;
    private String food;
    private int count;

    public final static String BaseUrl = "http://www.yi18.net/";

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * object. Subclasses are encouraged to override this method and provide an
     * implementation that takes into account the object's type and data. The
     * default implementation is equivalent to the following expression:
     * <pre>
     *   getClass().getName() + '@' + Integer.toHexString(hashCode())</pre>
     * <p>See <a href="{@docRoot}reference/java/lang/Object.html#writing_toString">Writing a useful
     * {@code toString} method</a>
     * if you intend implementing your own {@code toString} method.
     *
     * @return a printable representation of this object.
     */
    @Override
    public String toString() {
        return getName();
    }

    public static List<Long> getIdList(List<CookItem> cookItemList) {
        return Lists.transform(cookItemList, new Function<CookItem, Long>() {
            @Override
            public Long apply(CookItem input) {
                return input.getId();
            }
        });
    }
}
