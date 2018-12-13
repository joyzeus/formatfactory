package com.jay.formatfactory.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.jay.formatfactory.R;
import com.jay.formatfactory.util.Logger;

import java.io.IOException;

public class VideoPlayActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final String KEY_CURRENT_POSITION = "current_position";
    private static final String kEY_FILEPATH = "filepath";
    private static final String kEY_FILENAME = "filename";
    private MediaPlayer mediaPlayer;
    private String path;
    private String name;
    private SurfaceHolder holder;
    private SurfaceView surfaceView;

    public static void newActivity(Context context, String filePath, String fileName) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra(kEY_FILEPATH, filePath);
        intent.putExtra(kEY_FILENAME, fileName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Logger.d("----------- onCreate ----------");

        Intent intent = getIntent();
        path = intent.getStringExtra(kEY_FILEPATH);
        name = intent.getStringExtra(kEY_FILENAME);

        surfaceView = findViewById(R.id.surfaceview);
        holder = surfaceView.getHolder();
        holder.addCallback(this);

        playVideo(path, name);
    }

    private void playVideo(String path, String name) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(this, Uri.parse(path));
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.d("----------- onConfigurationChanged ----------");
        setContentView(R.layout.activity_video_player);

        surfaceView = findViewById(R.id.surfaceview);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Logger.d("----------- surfaceCreated ----------");
//        mediaPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Logger.d("----------- surfaceChanged ----------");
//        mediaPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        resume();
        Logger.d("----------- onResume ----------");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        pause();
        Logger.d("----------- onPause ----------");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        stop();
        Logger.d("----------- onDestroy ----------");
    }

    public void resume() {
//        if (mediaPlayer != null && mediaPlayer.ge()) {
//            mediaPlayer.start();
//        }
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}