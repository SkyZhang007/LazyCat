package com.sky.gank.douban;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.sky.gank.R;
import com.sky.gank.base.ItemViewModel;
import com.sky.gank.data.douban.DoubanMovieData;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/2 0002.
 **/
public class DoubanMovieItemViewModel extends ItemViewModel<DoubanMovieViewModel> {

    public DoubanMovieData.SubjectsBean bean;
    public Drawable drawableImg;

    public DoubanMovieItemViewModel(@NonNull DoubanMovieViewModel viewModel, DoubanMovieData.SubjectsBean subjectsBean) {
        super(viewModel);
        this.bean = subjectsBean;
        //ImageView的占位图片，可以解决RecyclerView中图片错误问题
        drawableImg = ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.ic_launcher);
    }
}
