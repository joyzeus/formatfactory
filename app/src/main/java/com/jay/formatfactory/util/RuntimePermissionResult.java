package com.jay.formatfactory.util;

public interface RuntimePermissionResult {

    void onGrant(int requestCode);

    void onDenied(int requestCode);

    void onNeverShowAgain(int requestCode);
}
