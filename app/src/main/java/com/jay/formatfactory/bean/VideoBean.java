package com.jay.formatfactory.bean;

public class VideoBean {

    private String data;
    private int size;
    private int addTime;
    private int modifyTime;
    private String mimeType;
    private String title;
    private String displayName;
    private int takenData;
    private int miniThumbMagic;
    private String bucketId;
    private String bucketDisplayName;
    private int duration;
    private String artist;
    private String album;
    private String resolation;
    private int width;
    private int height;

    public VideoBean(String data, int size, int addTime, int modifyTime, String mimeType, String title,
                     String displayName, int takenData, int miniThumbMagic, String bucketId, String bucketDisplayName,
                     int duration, String artist, String album, String resolation, int width, int height) {
        this.data = data;
        this.size = size;
        this.addTime = addTime;
        this.modifyTime = modifyTime;
        this.mimeType = mimeType;
        this.title = title;
        this.displayName = displayName;
        this.takenData = takenData;
        this.miniThumbMagic = miniThumbMagic;
        this.bucketId = bucketId;
        this.bucketDisplayName = bucketDisplayName;
        this.duration = duration;
        this.artist = artist;
        this.album = album;
        this.resolation = resolation;
        this.width = width;
        this.height = height;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getTakenData() {
        return takenData;
    }

    public void setTakenData(int takenData) {
        this.takenData = takenData;
    }

    public int getMiniThumbMagic() {
        return miniThumbMagic;
    }

    public void setMiniThumbMagic(int miniThumbMagic) {
        this.miniThumbMagic = miniThumbMagic;
    }

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketDisplayName() {
        return bucketDisplayName;
    }

    public void setBucketDisplayName(String bucketDisplayName) {
        this.bucketDisplayName = bucketDisplayName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getResolation() {
        return resolation;
    }

    public void setResolation(String resolation) {
        this.resolation = resolation;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "data='" + data + '\'' +
                ", size=" + size +
                ", addTime=" + addTime +
                ", modifyTime=" + modifyTime +
                ", mimeType='" + mimeType + '\'' +
                ", title='" + title + '\'' +
                ", displayName='" + displayName + '\'' +
                ", takenData=" + takenData +
                ", miniThumbMagic=" + miniThumbMagic +
                ", bucketId='" + bucketId + '\'' +
                ", bucketDisplayName='" + bucketDisplayName + '\'' +
                ", duration=" + duration +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", resolation='" + resolation + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}