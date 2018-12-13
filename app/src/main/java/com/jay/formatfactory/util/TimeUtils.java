package com.jay.formatfactory.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String formatTime(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(time));
    }
}
