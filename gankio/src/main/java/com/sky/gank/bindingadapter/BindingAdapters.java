package com.sky.gank.bindingadapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sky.gank.util.ViewUtil;

import java.util.List;

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
//                    .centerCrop()
                    .placeholder(placeholder)
                    .into(imageView);
        }
    }

    @BindingAdapter(value={"chipsList"},requireAll=false)
    public static void addChip(ChipGroup chipGroup, List<String> chipsList){
        Context context = ViewUtil.getActivityFromView(chipGroup);
        // 防止在 RecyclerView 中重复添加
        if(chipGroup.getChildCount() != 0){
            chipGroup.removeAllViews();
        }
        if(null != chipsList && !chipsList.isEmpty() && null != context){
            for(String text:chipsList){
                Chip chip = new Chip(context);
                chip.setText(text);
                chip.setChipCornerRadius(16f);
                chipGroup.addView(chip);
            }
        }
    }

}
