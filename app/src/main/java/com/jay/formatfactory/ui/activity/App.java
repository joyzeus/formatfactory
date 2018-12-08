package com.jay.formatfactory.ui.activity;

import android.app.Application;

import com.jay.formatfactory.util.SharedPreferenceUtil;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferenceUtil.init(this);
    }
}
