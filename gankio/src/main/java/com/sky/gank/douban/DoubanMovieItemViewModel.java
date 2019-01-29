package com.sky.gank.douban;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.sky.gank.R;
import com.sky.gank.base.ItemViewModel;
import com.sky.gank.command.BindingCommand;
import com.sky.gank.command.BindingConsumer;
import com.sky.gank.data.douban.DoubanMovieData;
import com.sky.gank.gank.GankActivity;
import com.sky.gank.util.ViewUtil;

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

    public BindingCommand<View> mItemClick = new BindingCommand<>(new BindingConsumer<View>() {
        @Override
        public void call(View view) {
            Context currentActivity = ViewUtil.getActivityFromView(view);
            if(null == currentActivity){
                return;
            }
            Intent intent = new Intent(currentActivity,GankActivity.class);
        }
    });
}
