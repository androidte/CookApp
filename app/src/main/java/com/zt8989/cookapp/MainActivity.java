package com.zt8989.cookapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.zt8989.cookapp.DAL.DataAccess;
import com.zt8989.cookapp.Model.CookClass;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener,
View.OnClickListener{
    private GridView mainGrid;
    private ImageView clearBtn;
    private ImageView searchBtn;
    private EditText searchText;

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CookClass cookClass = (CookClass)parent.getItemAtPosition(position);
        Intent intent=new Intent(MainActivity.this,SubListActivity.class);
        intent.putExtra("title", cookClass.getName());
        intent.putExtra("classId", cookClass.getId());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mainGrid = (GridView) findViewById(R.id.main_grid);
        clearBtn = (ImageView) findViewById(R.id.clear_btn);
        searchBtn = (ImageView) findViewById(R.id.search_btn);
        searchText = (EditText) findViewById(R.id.search_input);

        QuickAdapter<CookClass> adapter=new QuickAdapter<CookClass>(this,R.layout.grid_list_item,DataAccess.getCookClassList()) {
            @Override
            protected void convert(BaseAdapterHelper helper, CookClass item) {
                helper.setText(R.id.grid_list_text, item.getName())
                        .setImageResource(R.id.grid_list_icon, item.getImageId());
            }
        };
        mainGrid.setAdapter(adapter);
        mainGrid.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_btn:
                searchText.setText("");
                break;
            case R.id.search_btn:
                //TODO
                Toast.makeText(this,"you search is" + searchText.getText().toString(),Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
        }
    }
}
