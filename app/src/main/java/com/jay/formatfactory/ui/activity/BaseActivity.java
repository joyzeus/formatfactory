package com.jay.formatfactory.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jay.formatfactory.util.RuntimePermissionManager;
import com.jay.formatfactory.util.RuntimePermissionResult;


import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements RuntimePermissionResult {

    public abstract int getLayoutId();

    public abstract void initViews();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initViews();
    }

    public void requestRuntimePermission(int requestCode, @NonNull String perimission) {
        RuntimePermissionManager.requestPermission(this, requestCode, perimission, this);
    }

    public void requestRuntimePermissions(int requestCode, @NonNull String[] perimissions) {
        RuntimePermissionManager.requestPermissions(this, requestCode, perimissions, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RuntimePermissionManager.onRequestPermissionsResult(this, requestCode, permissions, grantResults, this);
    }

    @Override
    public void onGrant(int requestCode) {

    }

    @Override
    public void onDenied(int requestCode) {

    }

    @Override
    public void onNeverShowAgain(int requestCode) {

    }
}