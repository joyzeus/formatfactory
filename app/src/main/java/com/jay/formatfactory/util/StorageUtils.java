package com.jay.formatfactory.util;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

public class StorageUtils {

    public static String getExternalStorageTotalSize() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(externalStorageDirectory.getPath());
        long totalBytes = stat.getTotalBytes();
        return FileUtils.formatAutoUnit(totalBytes, FileUtils.Unit.UNIT_BYTE);
    }

    public static String getExternalStorageAvailableSize() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(externalStorageDirectory.getPath());
        long availableBytes = stat.getAvailableBytes();
        return FileUtils.formatAutoUnit(availableBytes, FileUtils.Unit.UNIT_BYTE);
    }

    public static String getExternalStorageFreeSize() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(externalStorageDirectory.getPath());
        long freeBytes = stat.getFreeBytes();
        return FileUtils.formatAutoUnit(freeBytes, FileUtils.Unit.UNIT_BYTE);
    }
}