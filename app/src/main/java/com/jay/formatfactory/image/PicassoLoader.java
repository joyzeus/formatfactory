package com.jay.formatfactory.image;

import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;

public class PicassoLoader implements ImageLoader {

    public Picasso picasso;

    public static ImageLoader with(Context context) {
        return null;
    }


    @Override
    public ImageLoader load(String path) {
        return null;
    }
}
