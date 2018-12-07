package com.jay.formatfactory.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;

public class RuntimePermissionManager {

    private static final String TAG = "PermissionManager";

    public static void requestPermission(@NonNull Activity activity, int requestCode,
                                          @NonNull String permission, @NonNull RuntimePermissionResult runtimePermissionResult) {
        requestPermissions(activity, requestCode, new String[]{permission}, runtimePermissionResult);
    }

    public static void requestPermissions(@NonNull Activity activity, int requestCode,
                                          @NonNull String[] permissions, @NonNull RuntimePermissionResult runtimePermissionResult) {
        ArrayList<String> unGrantPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                unGrantPermissionList.add(permission);
            }
        }

        if (unGrantPermissionList.size() == 0) {
            runtimePermissionResult.onGrant(requestCode);
        } else {
            String[] unGrantPermissionArray = new String[unGrantPermissionList.size()];
            unGrantPermissionList.toArray(unGrantPermissionArray);
            ActivityCompat.requestPermissions(activity, unGrantPermissionArray, requestCode);
        }
    }

    public static void onRequestPermissionsResult(@NonNull Activity activity, int requestCode, @NonNull String[] permissions,
                                                  @NonNull int[] grantResults, @NonNull RuntimePermissionResult runtimePermissionResult) {
        boolean isGrantAll = true;
        boolean isNevenShowAgainChecked = false;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                isGrantAll = false;
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[i])) {
                    isNevenShowAgainChecked = true;
                    break;
                }
            }
        }

        if (isGrantAll) {
            runtimePermissionResult.onGrant(requestCode);
        } else if (isNevenShowAgainChecked) {
            runtimePermissionResult.onNeverShowAgain(requestCode);
        } else {
            runtimePermissionResult.onDenied(requestCode);
        }
    }
}