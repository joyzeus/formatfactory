package com.jay.formatfactory.ui.fragment;

import android.support.v4.app.Fragment;

import com.jay.formatfactory.R;

public class VideoListFragment extends BaseFragment {
    public static Fragment getInstance() {
        return new VideoListFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video_list;
    }

    @Override
    public void initView() {

    }
}
