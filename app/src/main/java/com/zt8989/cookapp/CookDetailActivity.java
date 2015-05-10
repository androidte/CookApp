package com.zt8989.cookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.Window;

import com.zt8989.cookapp.DAL.CookDetailAdapter;
import com.zt8989.cookapp.Model.BaseCookItem;

import java.util.List;

/**
 * Created by Administrator on 2015/5/7.
 */
public class CookDetailActivity extends FragmentActivity{
    private TitleLayout titleLayout;
    private ViewPager viewPager;
    private CookDetailAdapter adapter;
    private List<BaseCookItem> list;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cook_detail_layout);

        titleLayout = (TitleLayout) findViewById(R.id.detail_title);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        //getCookDetail(intent.getLongExtra("classId",1));
        initView();
    }

    private void initView(){
        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        int position= args.getInt("position");
        list = (List<BaseCookItem>) args.getSerializable("List");
        updateTitle(list.get(position).getName());
        titleLayout.setBackgroundColor(0xFF0099CC);

        updateViewPager(position);
    }

    private void updateViewPager(int position) {
        adapter = new CookDetailAdapter(getSupportFragmentManager());
        adapter.addAll(list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                Fragment fragment = adapter.getItem(position);
                Bundle args=fragment.getArguments();
                BaseCookItem item = (BaseCookItem) args.getSerializable(CookDetailFragment.ARG_COOK_ITEM);
                updateTitle(item.getName());
            }
        });
    }

    public void updateTitle(String title) {
        titleLayout.setText(Html.fromHtml(title).toString());
    }
}
