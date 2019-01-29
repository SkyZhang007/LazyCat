package com.sky.gank.douban;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.support.annotation.NonNull;

import com.sky.gank.base.BaseViewModel;

import io.reactivex.subjects.PublishSubject;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/29 0029.
 **/
public class DoubanMovieDetailViewModel extends BaseViewModel {

    public DoubanMovieDetailViewModel(@NonNull Application application, PublishSubject<Lifecycle.Event> publishSubject) {
        super(application, publishSubject);
    }
}
