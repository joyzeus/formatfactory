package com.jay.formatfactory.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jay.formatfactory.FileManager;
import com.jay.formatfactory.R;
import com.jay.formatfactory.bean.VideoBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class FileExplorerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explorer);

        recyclerView = findViewById(R.id.video_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new VideoAdapter(null));
        Observable.just(this)
                .map(new Function<Context, List<VideoBean>>() {
                    @Override
                    public List<VideoBean> apply(Context context) throws Exception {
                        return FileManager.getVideos(context);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<VideoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<VideoBean> videoBeans) {
                        recyclerView.setAdapter(new VideoAdapter(videoBeans));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    static class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Holder> {

        List<VideoBean> videoBeanList;

        public VideoAdapter(List<VideoBean> videoBeanList) {
            this.videoBeanList = videoBeanList;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video_layout, null));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int i) {
            VideoBean videoBean = videoBeanList.get(i);
            Bitmap videoThumbnail = getVideoThumbnail(videoBean.getData());
            holder.imageView.setImageBitmap(videoThumbnail);
//            Picasso.get()
//                    .load(videoBean.getData())
//                    .into(holder.imageView);
            holder.titleNameView.setText(videoBean.getTitle());
        }

        @Override
        public int getItemCount() {
            return videoBeanList == null ? 0 : videoBeanList.size();
        }

        class Holder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView titleNameView;

            public Holder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.item_video_imageview);
                titleNameView = itemView.findViewById(R.id.item_video_titleview);
            }
        }
    }

    public static Bitmap getVideoThumbnail(String filePath) {
        Bitmap b=null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            b=retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();

        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return b;
    }
}