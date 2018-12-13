package com.jay.formatfactory.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jay.formatfactory.media.FileManager;
import com.jay.formatfactory.R;
import com.jay.formatfactory.bean.VideoBean;
import com.jay.formatfactory.ui.interf.OnItemClickListener;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;


public class FileExplorerActivity extends AppCompatActivity implements OnItemClickListener<VideoBean> {

    private static final String TAG = "TAG";
    private RecyclerView recyclerView;

    class ObservableSubscribeHooker<T> implements Observer<T> {
        private Observer<T> actual;

        public ObservableSubscribeHooker(Observer<T> actual) {
            this.actual = actual;
        }

        @Override
        public void onSubscribe(Disposable d) {
            actual.onSubscribe(d);
        }

        @Override
        public void onNext(T t) {
            hookOnNext(t);
            actual.onNext(t);
        }

        private void hookOnNext(T t) {
//            if (t instanceof List){
                Log.e(TAG, "hookOnNext");
//            }
        }

        @Override
        public void onError(Throwable e) {

            if (e instanceof ConnectException) {
                Log.e(TAG, "Connect failed: ", e);
                return;
            }

            if (e instanceof SocketTimeoutException) {
                Log.e(TAG, "Time out ", e);
                return;
            }
            actual.onError(e);
        }

        @Override
        public void onComplete() {
            actual.onComplete();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explorer);

        recyclerView = findViewById(R.id.video_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new VideoAdapter(null));
        RxJavaPlugins.setOnObservableSubscribe(new BiFunction<Observable, Observer, Observer>() {
            @Override
            public Observer apply(Observable observable, Observer observer) throws Exception {
                return new ObservableSubscribeHooker(observer);
            }
        });

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
                        VideoAdapter videoAdapter = new VideoAdapter(videoBeans);
                        videoAdapter.setOnItemClickListener(FileExplorerActivity.this);
                        recyclerView.setAdapter(videoAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onItemClick(int position, VideoBean videoBean) {
        VideoPlayActivity.newActivity(this, videoBean.getData(), videoBean.getTitle());
    }

    static class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Holder> implements View.OnClickListener {

        List<VideoBean> videoBeanList;
        private OnItemClickListener<VideoBean> onItemClickListener;

        public VideoAdapter(List<VideoBean> videoBeanList) {
            this.videoBeanList = videoBeanList;
        }

        public void setOnItemClickListener(OnItemClickListener<VideoBean> onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
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
            holder.itemView.setTag(i);
        }

        @Override
        public int getItemCount() {
            return videoBeanList == null ? 0 : videoBeanList.size();
        }

        @Override
        public void onClick(View v) {
            Integer position = (Integer) v.getTag();
            if (onItemClickListener != null) {
                VideoBean videoBean = videoBeanList.get(position);
                onItemClickListener.onItemClick(position, videoBean);
            }
        }

        class Holder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView titleNameView;

            public Holder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.item_video_imageview);
                titleNameView = itemView.findViewById(R.id.item_video_titleview);
                itemView.setOnClickListener(VideoAdapter.this);
            }
        }
    }

    public static Bitmap getVideoThumbnail(String filePath) {
        Bitmap b = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            b = retriever.getFrameAtTime();
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