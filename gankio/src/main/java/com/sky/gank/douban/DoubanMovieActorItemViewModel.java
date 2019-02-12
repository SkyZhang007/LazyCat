package com.sky.gank.douban;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.sky.gank.R;
import com.sky.gank.data.douban.DoubanMovieStaff;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/2/12 0012.
 **/
public class DoubanMovieActorItemViewModel{

    public DoubanMovieStaff mMovieStaff;
    public Drawable drawableImg;

    public DoubanMovieActorItemViewModel(Context context,DoubanMovieStaff doubanMovieStaff) {
        this.mMovieStaff = doubanMovieStaff;
        drawableImg = ContextCompat.getDrawable(context, R.mipmap.ic_launcher);
    }

}
