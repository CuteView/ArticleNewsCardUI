package com.deekea.articlenewscardui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.deekea.articlenewscardui.model.NewsModel;
import com.deekea.articlenewscardui.ui.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private List<NewsModel> mNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalDisplay.init(getApplicationContext());

        setContentView(R.layout.activity_main);

        mNewsList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            NewsModel model = new NewsModel();
            mNewsList.add(model);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        // 设置LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mNewsAdapter = new NewsAdapter(this, mNewsList);
        mRecyclerView.setAdapter(mNewsAdapter);


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
}
