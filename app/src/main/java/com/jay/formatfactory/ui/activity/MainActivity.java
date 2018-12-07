package com.jay.formatfactory.ui.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.jay.formatfactory.R;
import com.jay.formatfactory.ui.adapter.HomeAdapter;

public class MainActivity extends BaseActivity {

    private static final int STROGAE_CODE = 1;

    private RecyclerView mRecyclerView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestRuntimePermission(STROGAE_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setTitle("");

        mRecyclerView = findViewById(R.id.main_recyclerview);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(new HomeAdapter(this));
    }

    @Override
    public void onGrant(int requestCode) {

    }
}