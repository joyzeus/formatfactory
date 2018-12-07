package com.jay.formatfactory.image;

import android.content.Context;

public class ImageLoaderProxy  {

    public ImageLoader with(Context context) {
        return PicassoLoader.with(context);
    }
}
