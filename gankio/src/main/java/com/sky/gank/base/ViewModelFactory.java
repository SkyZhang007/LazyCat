package com.sky.gank.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.sky.gank.data.douban.DoubanMovieDataSource;
import com.sky.gank.data.gank.GankDataSource;
import com.sky.gank.data.meizi.MeiziDataSource;
import com.sky.gank.douban.DoubanMovieDetailViewModel;
import com.sky.gank.douban.DoubanMovieViewModel;
import com.sky.gank.gank.GankViewModel;
import com.sky.gank.meizi.MeiziViewModel;

import io.reactivex.subjects.PublishSubject;

/**
 * 类名称：
 * 类功能：
 * 类作者：goldze
 * 类日期：2019/1/15 0015.
 **/
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;
    private PublishSubject<Lifecycle.Event> mLifeEvent;
    private static BaseDataSource mBaseDataSource;

    public static ViewModelFactory getInstance(Application application, PublishSubject<Lifecycle.Event> publishSubject,
                                               BaseDataSource baseDataSource) {
        mBaseDataSource = baseDataSource;
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application,publishSubject,baseDataSource);
                }
            }
        }
        return INSTANCE;
    }


    private ViewModelFactory(Application application,PublishSubject<Lifecycle.Event> publishSubject,
                             BaseDataSource baseDataSource) {
        mApplication = application;
        mLifeEvent = publishSubject;
        mBaseDataSource = baseDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MeiziViewModel.class)) {
            return (T) new MeiziViewModel(mApplication,mLifeEvent);
        } else if(modelClass.isAssignableFrom(DoubanMovieViewModel.class)){
            return (T) new DoubanMovieViewModel(mApplication,mLifeEvent);
        } else if(modelClass.isAssignableFrom(GankViewModel.class)){
            return (T) new GankViewModel(mApplication,mLifeEvent);
        } else if(modelClass.isAssignableFrom(DoubanMovieDetailViewModel.class)){
            return (T) new DoubanMovieDetailViewModel(mApplication,mLifeEvent);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    /**
     * 创建ViewModel
     * @param cls
     * @param <T>
     * @return
     */
    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity,this).get(cls);
    }
}
