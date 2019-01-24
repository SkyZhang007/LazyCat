package com.sky.gank.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/7 0007.
 **/
public class BindingAdapters {

    @BindingAdapter(value={"imageUrl","placeholder"},requireAll=false)
    public static void setImageUrl(ImageView imageView, String imageUrl, Drawable placeholder) {
        if (!TextUtils.isEmpty(imageUrl)) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(400,400)
                    .centerCrop()
                    .placeholder(placeholder)
                    .into(imageView);
        }
    }

}
