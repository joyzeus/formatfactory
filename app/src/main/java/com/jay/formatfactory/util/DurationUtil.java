package com.jay.formatfactory.util;

public class DurationUtil {

    public static String formatAudioDuration(int duration){
        duration = duration / 1000;
        StringBuilder stringBuilder = new StringBuilder();
        if(duration <= 0){
            stringBuilder.append("00:00");
        }else if (duration < 10){
            stringBuilder.append("00:0");
            stringBuilder.append(duration);
        }else if (duration < 60){
            stringBuilder.append("00:");
            stringBuilder.append(duration);
        }else {
            int i = duration / 60;
            int mod = duration % 10;
            if (i < 10){
                stringBuilder.append("0");
            }
            stringBuilder.append(i);
            stringBuilder.append(":");

            if (mod < 10){
                stringBuilder.append("0");
            }
            stringBuilder.append(mod);
        }
        return stringBuilder.toString();
    }
}
