package com.jay.formatfactory.bean;

public class AudioBean {

    private String data;

    private int size;

    private int addTime;

    private int modifyTime;

    private String mimeType;

    private String title;

    private int isRingtone;

    private int isMusic;

    private int isAlarm;

    private int isNotification;

    private int isPodcast;

    public AudioBean(String data, int size, int addTime, int modifyTime, String mimeType,
                     String title, int isRingtone, int isMusic, int isAlarm, int isNotification, int isPodcast) {
        this.data = data;
        this.size = size;
        this.addTime = addTime;
        this.modifyTime = modifyTime;
        this.mimeType = mimeType;
        this.title = title;
        this.isRingtone = isRingtone;
        this.isMusic = isMusic;
        this.isAlarm = isAlarm;
        this.isNotification = isNotification;
        this.isPodcast = isPodcast;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(int modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsRingtone() {
        return isRingtone;
    }

    public void setIsRingtone(int isRingtone) {
        this.isRingtone = isRingtone;
    }

    public int getIsMusic() {
        return isMusic;
    }

    public void setIsMusic(int isMusic) {
        this.isMusic = isMusic;
    }

    public int getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(int isAlarm) {
        this.isAlarm = isAlarm;
    }

    public int getIsNotification() {
        return isNotification;
    }

    public void setIsNotification(int isNotification) {
        this.isNotification = isNotification;
    }

    public int getIsPodcast() {
        return isPodcast;
    }

    public void setIsPodcast(int isPodcast) {
        this.isPodcast = isPodcast;
    }

    @Override
    public String toString() {
        return "AudioBean{" +
                "data='" + data + '\'' +
                ", size=" + size +
                ", addTime=" + addTime +
                ", modifyTime=" + modifyTime +
                ", mimeType='" + mimeType + '\'' +
                ", title='" + title + '\'' +
                ", isRingtone=" + isRingtone +
                ", isMusic=" + isMusic +
                ", isAlarm=" + isAlarm +
                ", isNotification=" + isNotification +
                ", isPodcast=" + isPodcast +
                '}';
    }
}