package com.sky.gank.douban;

import com.sky.gank.R;
import com.sky.gank.BR;
import com.sky.gank.base.BaseAppCompatActivity;
import com.sky.gank.databinding.ActivityDoubanMovieDetailBinding;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/29 0029.
 **/
public class DoubanMovieDetailActivity extends BaseAppCompatActivity<ActivityDoubanMovieDetailBinding,DoubanMovieDetailViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_douban_movie_detail;
    }

    @Override
    protected int initVariableId() {
        return BR.movieDetailViewModel;
    }

    @Override
    protected DoubanMovieDetailViewModel initViewModel() {
        return null;
    }
}
