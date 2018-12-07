package com.jay.formatfactory.util;

import android.util.Log;

public class Logger {

    private static final boolean ENABLE_ERROR = true;
    private static final boolean DEBUG = true;

    private static final String TAG = "formatfactory";

    public static void d(String message) {
        d(TAG, message);
    }

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(tag, "----------  " + message + "  ----------");
        }
    }

    public static void e(String message) {
        e(TAG, message);
    }

    public static void e(String tag, String message) {
        if (ENABLE_ERROR) {
            Log.e(tag, "----------  " + message + "  ----------");
        }
    }
}