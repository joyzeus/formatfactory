package com.jay.formatfactory.bean;

public class ImageBean {

    private String data;
    private int size;
    private int addTime;
    private int modifyTime;
    private String mimeType;
    private String title;
    private String displayName;
    private int orientation;
    private int takenData;
    private int miniThumbMagic;
    private String bucketId;
    private String bucketDisplayName;
    private int width;
    private int height;

    public ImageBean(String data, int size, int addTime, int modifyTime,
                     String mimeType, String title, String displayName, int orientation, int takenData,
                     int miniThumbMagic, String bucketId, String bucketDisplayName, int width, int height) {
        this.data = data;
        this.size = size;
        this.addTime = addTime;
        this.modifyTime = modifyTime;
        this.mimeType = mimeType;
        this.title = title;
        this.displayName = displayName;
        this.orientation = orientation;
        this.takenData = takenData;
        this.miniThumbMagic = miniThumbMagic;
        this.bucketId = bucketId;
        this.bucketDisplayName = bucketDisplayName;
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

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
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
        return "ImageBean{" +
                "data='" + data + '\'' +
                ", size=" + size +
                ", addTime=" + addTime +
                ", modifyTime=" + modifyTime +
                ", mimeType='" + mimeType + '\'' +
                ", title='" + title + '\'' +
                ", displayName='" + displayName + '\'' +
                ", orientation=" + orientation +
                ", takenData=" + takenData +
                ", miniThumbMagic=" + miniThumbMagic +
                ", bucketId='" + bucketId + '\'' +
                ", bucketDisplayName='" + bucketDisplayName + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}