package com.jay.formatfactory.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.jay.formatfactory.util.RuntimePermissionManager;
import com.jay.formatfactory.util.RuntimePermissionResult;

public abstract class BaseActivity extends AppCompatActivity implements RuntimePermissionResult {

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