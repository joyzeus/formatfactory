package com.jay.formatfactory.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jay.formatfactory.R;
import com.jay.formatfactory.ui.adapter.FileAdapter;
import com.jay.formatfactory.ui.interf.OnItemClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class FileFragment extends BaseFragment implements OnItemClickListener<File> {

    private static final String KEY_PARENT_PATH = "parent_path";

    @BindView(R.id.file_recyclerview)
    RecyclerView mFileRecyclerView;

    private FileAdapter mFileAdapter;
    private String mParentPath;
    private File mRootFile;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_file;
    }

    @Override
    public void initView(View view) {
        mFileRecyclerView = view.findViewById(R.id.file_recyclerview);
        mFileRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFileAdapter = new FileAdapter(null);
        mFileAdapter.setOnItemClickListener(this);
        mFileRecyclerView.setAdapter(mFileAdapter);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mParentPath = arguments.getString(KEY_PARENT_PATH);
            File file = new File(mParentPath);
            if (file.exists()) {
                listChildFile(file);
            } else {
                return;
            }
        } else {

            return;
        }
    }

    private void listChildFile(File file) {
        mRootFile = file;
        Observable.just(file)
                .map(new Function<File, List<File>>() {
                    @Override
                    public List<File> apply(final File file) throws Exception {
                        File[] files = file.listFiles();
                        ArrayList<File> fileList = new ArrayList<>();
                        for (File childFile: files) {
                            if (!childFile.isHidden()){
                                fileList.add(childFile);
                            }
                        }
                        Collections.sort(fileList, new Comparator<File>() {
                            @Override
                            public int compare(File o1, File o2) {
                                int result = 0;
                                if (o1.isDirectory() ^ o2.isDirectory()) {
                                    if (o1.isDirectory()) {
                                        result = -1;
                                    } else {
                                        result = 1;
                                    }
                                } else {
                                    result = o1.getName().compareToIgnoreCase(o2.getName());
                                }
                                return result;
                            }
                        });
                        return fileList;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<File>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<File> files) {
                        mFileAdapter.setFileList(files);
                        mFileAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static FileFragment getInstance(File parentPath) {
        FileFragment fileFragment = new FileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PARENT_PATH, parentPath.getAbsolutePath());
        fileFragment.setArguments(bundle);
        return fileFragment;
    }

    @Override
    public void onItemClick(int position, File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                listChildFile(file);
            } else {
                Toast.makeText(getContext(), "这是一个文件", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean onBackPress() {
        if (mRootFile != null){
            if (mRootFile.getAbsolutePath().equals(mParentPath)){
                return false;
            }else {
                listChildFile(mRootFile.getParentFile());
                return true;
            }
        }
        return false;
    }
}