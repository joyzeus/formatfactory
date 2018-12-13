package com.jay.formatfactory.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jay.formatfactory.R;
import com.jay.formatfactory.ui.activity.FileExplorerActivity;
import com.jay.formatfactory.ui.interf.OnItemClickListener;

import java.nio.file.attribute.PosixFileAttributes;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> implements View.OnClickListener {

    private Context context;
    private String[] fileTypeArray;
    private int[] fileTypeIconArray;
    private OnItemClickListener mOnItemClickListener;

    public HomeAdapter(@NonNull Context context) {
        this.context = context;
        fileTypeArray = context.getResources().getStringArray(R.array.fileTypeArray);
        fileTypeIconArray = new int[fileTypeArray.length];
        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.fileTypeIconArray);
        for (int i = 0; i < fileTypeArray.length; i++) {
            fileTypeIconArray[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_file_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.iconImageView.setImageResource(fileTypeIconArray[i]);
        holder.typeNameTextview.setText(fileTypeArray[i]);

        holder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return fileTypeArray == null ? 0 : fileTypeArray.length;
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(tag, null);
        }
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView iconImageView;
        TextView typeNameTextview;
        TextView countTextview;

        public Holder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.icon_imageview);
            typeNameTextview = itemView.findViewById(R.id.typename_textview);
            countTextview = itemView.findViewById(R.id.count_textview);

            itemView.setOnClickListener(HomeAdapter.this);
        }
    }
}