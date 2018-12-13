package com.jay.formatfactory.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jay.formatfactory.R;
import com.jay.formatfactory.ui.interf.OnItemClickListener;
import com.jay.formatfactory.util.FileUtils;
import com.jay.formatfactory.util.TimeUtils;

import java.io.File;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.Holder> implements View.OnClickListener {

    private List<File> fileList;
    private OnItemClickListener<File> mOnItemClickListener;

    public FileAdapter(List<File> fileList) {
        this.fileList = fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public void setOnItemClickListener(OnItemClickListener<File> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_file_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        File file = fileList.get(position);
        int fileIcon = R.drawable.folder;
        if (!file.isDirectory()) {
            fileIcon = R.drawable.file;
        }
        holder.fileIconImageview.setImageResource(fileIcon);
        holder.filenameTextview.setText(file.getName());
        if (file.isFile()){
            holder.filesizeTextview.setText(FileUtils.formatAutoUnit(file.length(), FileUtils.Unit.UNIT_BYTE));
        }else{
            holder.filesizeTextview.setText(R.string.strDirectory);
        }
        holder.filetimeTextview.setText(TimeUtils.formatTime(file.lastModified()));

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return fileList == null ? 0 : fileList.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(position, fileList.get(position));
        }
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView fileIconImageview;
        TextView filenameTextview;
        TextView filesizeTextview;
        TextView filetimeTextview;

        public Holder(@NonNull View itemView) {
            super(itemView);
            fileIconImageview = itemView.findViewById(R.id.file_icon_imageview);
            filenameTextview = itemView.findViewById(R.id.filename_textview);
            filesizeTextview = itemView.findViewById(R.id.filesize_textview);
            filetimeTextview = itemView.findViewById(R.id.filetime_textview);

            itemView.setOnClickListener(FileAdapter.this);
        }
    }
}