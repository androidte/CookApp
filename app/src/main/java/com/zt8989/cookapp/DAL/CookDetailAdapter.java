package com.zt8989.cookapp.DAL;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.zt8989.cookapp.CookDetailFragment;
import com.zt8989.cookapp.Model.BaseCookItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhou on 2015/5/10.
 */
public class CookDetailAdapter extends FragmentStatePagerAdapter {
    private List<CookDetailFragment> fragments=new ArrayList<>();

    public CookDetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addAll(List<BaseCookItem> list) {
        fragments.addAll(Lists.transform(list, new Function<BaseCookItem, CookDetailFragment>() {
            @Override
            public CookDetailFragment apply(BaseCookItem input) {
                return CookDetailFragment.newInstance(input);
            }
        }));
    }
}
