package com.jay.formatfactory.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jay.formatfactory.R;
import com.jay.formatfactory.bean.ImageBean;
import com.jay.formatfactory.media.FileManager;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PictureListFragment extends BaseFragment {

    @BindView(R.id.picture_recyclerview)
    RecyclerView mRecyclerView;
    private PictureAdapter mPictureAdapter;

    public static PictureListFragment getInstance() {
        return new PictureListFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_picture_list;
    }

    @Override
    public void initView() {
        mPictureAdapter = new PictureAdapter(null);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setAdapter(mPictureAdapter);

        Observable.just(getContext())
                .map(new Function<Context, List<ImageBean>>() {
                    @Override
                    public List<ImageBean> apply(Context context) throws Exception {
                        return FileManager.getPictures(context);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ImageBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ImageBean> imageBeans) {
                        mPictureAdapter.setImageBeanList(imageBeans);
                        mPictureAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.Holder> {

        public List<ImageBean> mImageBeanList;

        public PictureAdapter(List<ImageBean> imageBeanList) {
            mImageBeanList = imageBeanList;
        }

        public void setImageBeanList(List<ImageBean> imageBeanList) {
            mImageBeanList = imageBeanList;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_picture_layout, null));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int i) {
            ImageBean imageBean = mImageBeanList.get(i);
            Picasso.get()
                    .load(new File(imageBean.getData()))
                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mImageBeanList == null ? 0 : mImageBeanList.size();
        }

        class Holder extends RecyclerView.ViewHolder {

            ImageView mImageView;

            public Holder(@NonNull View itemView) {
                super(itemView);
                mImageView  = itemView.findViewById(R.id.imageView);
            }
        }
    }
}
