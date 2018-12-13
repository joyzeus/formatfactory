package com.jay.formatfactory.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jay.formatfactory.R;
import com.jay.formatfactory.media.FileManager;
import com.jay.formatfactory.ui.adapter.HomeAdapter;
import com.jay.formatfactory.ui.fragment.FileFragment;
import com.jay.formatfactory.ui.interf.OnItemClickListener;
import com.jay.formatfactory.util.Logger;

import java.io.File;

import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnItemClickListener {

    private static final int STROGAE_CODE = 1;

    @BindView(R.id.main_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_container)
    RelativeLayout mRelativeLayout;
    private Toolbar mToolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        requestRuntimePermission(STROGAE_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(this);
        mRecyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(this);

        showEternalStorage();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_phone) {
            showEternalStorage();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_audio) {

        } else if (id == R.id.nav_video) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(int position, Object o) {
        switch (position) {
            case 0:
                Intent intent = new Intent(this, FileExplorerActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(this, MusicListActivity.class);
                startActivity(intent1);
                break;

        }
    }

    private void showEternalStorage(){
        mToolbar.setTitle(R.string.strPhone);
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            FileFragment fileFragment = FileFragment.getInstance(externalStorageDirectory);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, fileFragment, "file");
            transaction.commit();
        } else {
            Toast.makeText(this, "未发现sd卡", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPictureFragment(){

    }

    private void showAudioFragment(){

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FileFragment fileFragment = (FileFragment) fragmentManager.findFragmentByTag("file");
            if (fileFragment != null && fileFragment.onBackPress()){
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
