package com.zt8989.cookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.zt8989.cookapp.DAL.CookDetailAdapter;
import com.zt8989.cookapp.Model.CookItem;

import java.util.List;

/**
 * Created by Administrator on 2015/5/7.
 */
public class CookDetailActivity extends FragmentActivity{
    private TitleLayout titleLayout;
    private ViewPager viewPager;
    private CookDetailAdapter adapter;
    private List<CookItem> cookItemList;

    //private TextView contentView;
    //private ImageView cookImg;

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI, calling
     * {@link #managedQuery(android.net.Uri, String[], String, String[], String)} to retrieve
     * cursors for data being displayed, etc.
     * <p/>
     * <p>You can call {@link #finish} from within this function, in
     * which case onDestroy() will be immediately called without any of the rest
     * of the activity lifecycle ({@link #onStart}, {@link #onResume},
     * {@link #onPause}, etc) executing.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cook_detail_layout);

        titleLayout = (TitleLayout) findViewById(R.id.detail_title);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        int position= args.getInt("position");
        cookItemList = (List<CookItem>) args.getSerializable("List");
        updateTitle(cookItemList.get(position).getName());

        titleLayout.setBackgroundColor(0xFF0099CC);
        updateViewPager(position);
        //getCookDetail(intent.getLongExtra("classId",1));
    }

    private void updateViewPager(int position) {
        adapter = new CookDetailAdapter(getSupportFragmentManager());
        adapter.addAll(cookItemList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                Fragment fragment = adapter.getItem(position);
                Bundle args=fragment.getArguments();
                CookItem item = (CookItem) args.getSerializable(CookDetailFragment.ARG_COOK_ITEM);
                updateTitle(item.getName());
            }
        });
    }

    public void updateTitle(String title) {
        titleLayout.setText(title);
    }
}
