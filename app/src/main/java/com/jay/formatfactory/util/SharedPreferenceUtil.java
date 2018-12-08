package com.jay.formatfactory.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {

    private static Context sContext;

    private static final String SHAREDPREFERENCES_FILENAME = "config";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
        sharedPreferences = sContext.getSharedPreferences(SHAREDPREFERENCES_FILENAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static int getInt(String key, int defalutValue) {
        return sharedPreferences.getInt(key, defalutValue);
    }

    public static String getString(String key, String defalutValue) {
        return sharedPreferences.getString(key, defalutValue);
    }

    public static boolean getBoolean(String key, boolean defalutValue) {
        return sharedPreferences.getBoolean(key, defalutValue);
    }
}