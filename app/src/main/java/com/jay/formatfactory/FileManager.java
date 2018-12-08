package com.jay.formatfactory;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.jay.formatfactory.bean.AudioBean;
import com.jay.formatfactory.bean.ImageBean;
import com.jay.formatfactory.bean.VideoBean;
import com.jay.formatfactory.util.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String TAG = "FileManager";

    public interface FileColumns extends MediaStore.MediaColumns {
        /**
         * The MTP storage ID of the file
         * <P>Type: INTEGER</P>
         * @hide
         */
        public static final String STORAGE_ID = "storage_id";

        /**
         * The MTP format code of the file
         * <P>Type: INTEGER</P>
         * @hide
         */
        public static final String FORMAT = "format";

        /**
         * The index of the parent directory of the file
         * <P>Type: INTEGER</P>
         */
        public static final String PARENT = "parent";

        /**
         * The MIME type of the file
         * <P>Type: TEXT</P>
         */
        public static final String MIME_TYPE = "mime_type";

        /**
         * The title of the content
         * <P>Type: TEXT</P>
         */
        public static final String TITLE = "title";

        /**
         * The media type (audio, video, image or playlist)
         * of the file, or 0 for not a media file
         * <P>Type: TEXT</P>
         */
        public static final String MEDIA_TYPE = "media_type";

        /**
         * Constant for the {@link #MEDIA_TYPE} column indicating that file
         * is not an audio, image, video or playlist file.
         */
        public static final int MEDIA_TYPE_NONE = 0;

        /**
         * Constant for the {@link #MEDIA_TYPE} column indicating that file is an image file.
         */
        public static final int MEDIA_TYPE_IMAGE = 1;

        /**
         * Constant for the {@link #MEDIA_TYPE} column indicating that file is an audio file.
         */
        public static final int MEDIA_TYPE_AUDIO = 2;

        /**
         * Constant for the {@link #MEDIA_TYPE} column indicating that file is a video file.
         */
        public static final int MEDIA_TYPE_VIDEO = 3;

        /**
         * Constant for the {@link #MEDIA_TYPE} column indicating that file is a playlist file.
         */
        public static final int MEDIA_TYPE_PLAYLIST = 4;
    }

    private static final String[] IMAGE_QUERY_PROJECT = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.ORIENTATION,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.MINI_THUMB_MAGIC,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT,
    };

    private static final String[] AUDIO_QUERY_PROJECT = {
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATE_MODIFIED,
            MediaStore.Audio.Media.MIME_TYPE,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.IS_RINGTONE,
            MediaStore.Audio.Media.IS_MUSIC,
            MediaStore.Audio.Media.IS_ALARM,
            MediaStore.Audio.Media.IS_NOTIFICATION,
            MediaStore.Audio.Media.IS_PODCAST,
    };

    private static final String[] VIDEO_QUERY_PROJECT = {
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DATE_MODIFIED,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATE_TAKEN,
            MediaStore.Video.Media.MINI_THUMB_MAGIC,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.ARTIST,
            MediaStore.Video.Media.ALBUM,
            MediaStore.Video.Media.RESOLUTION,
            MediaStore.Video.Media.WIDTH,
            MediaStore.Video.Media.HEIGHT
    };

    public static List<ImageBean> getPictures(@NonNull Context context){
        ArrayList<ImageBean> imageList = new ArrayList<>();
        Cursor cursor =  context.getContentResolver() .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                IMAGE_QUERY_PROJECT, null, null, null);
        if (cursor != null){

            while(cursor.moveToNext()){
                String path = cursor.getString(0);
                int size = cursor.getInt(1);
                int addDate = cursor.getInt(2);
                int modifyDate = cursor.getInt(3);
                String mimeType = cursor.getString(4);
                String title = cursor.getString(5);
                String displayName = cursor.getString(6);
                int orientation = cursor.getInt(7);
                int takenDate = cursor.getInt(8);
                int miniThumbMagic = cursor.getInt(9);
                String bucketId = cursor.getString(10);
                String bucketDisplayName = cursor.getString(11);
                int width = cursor.getInt(12);
                int height = cursor.getInt(13);

                ImageBean imageBean = new ImageBean(path, size, addDate, modifyDate, mimeType, title, displayName,
                        orientation, takenDate, miniThumbMagic, bucketId, bucketDisplayName, width, height);
                Logger.d(imageBean.toString());
                imageList.add(imageBean);
            }

            cursor.close();
        }
        return imageList;
    }

    public static List<AudioBean> getAudios(@NonNull Context context){
        ArrayList<AudioBean> audioList = new ArrayList<>();
        Cursor cursor =  context.getContentResolver() .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                AUDIO_QUERY_PROJECT, null, null, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                String path = cursor.getString(0);
                int size = cursor.getInt(1);
                int addDate = cursor.getInt(2);
                int modifyDate = cursor.getInt(3);
                String mimeType = cursor.getString(4);
                String title = cursor.getString(5);
                int isRingtone = cursor.getInt(6);
                int isMusic = cursor.getInt(7);
                int isAlarm = cursor.getInt(8);
                int isNotification = cursor.getInt(9);
                int isPodcast = cursor.getInt(10);

                AudioBean audioBean = new AudioBean(path, size, addDate, modifyDate, mimeType, title,
                        isRingtone, isMusic, isAlarm, isNotification, isPodcast);
                Logger.d(audioBean.toString());
                audioList.add(audioBean);
            }
            cursor.close();
        }
        return audioList;
    }

    public static List<VideoBean> getVideos(@NonNull Context context){
        ArrayList<VideoBean> videoList = new ArrayList<>();
        Cursor cursor =  context.getContentResolver() .query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                VIDEO_QUERY_PROJECT, null, null, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                String data = cursor.getString(0);
                File file = new File(data);
                if (!file.exists()){
                    continue;
                }
                int size = cursor.getInt(1);
                int addTime = cursor.getInt(2);
                int modifyTime = cursor.getInt(3);
                String mimeType = cursor.getString(4);
                String title = cursor.getString(5);
                String displayName = cursor.getString(6);
                int takenData = cursor.getInt(7);
                int miniThumbMagic = cursor.getInt(8);
                String bucketId = cursor.getString(9);
                String bucketDisplayName = cursor.getString(10);
                int duration = cursor.getInt(11);
                String artist = cursor.getString(12);
                String album = cursor.getString(13);
                String resolation = cursor.getString(14);
                int width = cursor.getInt(15);
                int height = cursor.getInt(16);

                VideoBean videoBean = new VideoBean(data, size, addTime, modifyTime, mimeType, title,
                        displayName, takenData, miniThumbMagic, bucketId, bucketDisplayName, duration,
                        artist, album, resolation, width, height);
                Logger.d(videoBean.toString());
                videoList.add(videoBean);
            }
            cursor.close();
        }
        return videoList;
    }
}