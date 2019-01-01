package com.jay.formatfactory.ui.fragment;

import android.support.v4.app.Fragment;

import com.jay.formatfactory.R;

public class MusicListFragment extends BaseFragment {
    public static Fragment getInstance() {
        return new MusicListFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_music_list;
    }

    @Override
    public void initView() {

    }
}
