package com.jay.formatfactory.ui.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jay.formatfactory.R;
import com.jay.formatfactory.bean.AudioBean;
import com.jay.formatfactory.media.FileManager;
import com.jay.formatfactory.util.DurationUtil;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MusicListActivity extends BaseActivity {

    @BindView(R.id.music_recyclerview)
    RecyclerView mRecyclerView;
    private MusicAdapter mMusicAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_music_list;
    }

    @Override
    public void initViews() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMusicAdapter = new MusicAdapter(null);
        mRecyclerView.setAdapter(mMusicAdapter);

        Observable.just(this)
                .map(new Function<Context, List<AudioBean>>() {
                    @Override
                    public List<AudioBean> apply(Context context) throws Exception {
                        return FileManager.getAudios(context);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AudioBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<AudioBean> audioBeans) {
                        mMusicAdapter.setMusicList(audioBeans);
                        mMusicAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.Holder> {

        private List<AudioBean> musicList;

        public MusicAdapter(List<AudioBean> musicList) {
            this.musicList = musicList;
        }

        public void setMusicList(List<AudioBean> musicList) {
            this.musicList = musicList;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_audio_layout, null));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int i) {
            AudioBean audioBean = musicList.get(i);
            holder.musicNameTextview.setText(audioBean.getTitle());
            holder.musicArtistTextview.setText(audioBean.getArtist() + " - " + audioBean.getAlbum());
            holder.musicDurationTextview.setText(DurationUtil.formatAudioDuration(audioBean.getDuration()));
        }

        @Override
        public int getItemCount() {
            return musicList == null ? 0 : musicList.size();
        }

        class Holder extends RecyclerView.ViewHolder {

            ImageView musicAlbumImageview;
            TextView musicNameTextview;
            TextView musicArtistTextview;
            TextView musicDurationTextview;

            public Holder(@NonNull View itemView) {
                super(itemView);
                musicAlbumImageview = itemView.findViewById(R.id.music_imageview);
                musicNameTextview = itemView.findViewById(R.id.music_name_textview);
                musicArtistTextview = itemView.findViewById(R.id.music_artist_textview);
                musicDurationTextview = itemView.findViewById(R.id.music_duration_textview);

            }
        }
    }
}