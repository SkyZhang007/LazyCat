package com.sky.gank.douban;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.sky.gank.base.BaseResponse;
import com.sky.gank.base.BaseViewModel;
import com.sky.gank.data.douban.DoubanMovieDataSource;
import com.sky.gank.data.douban.DoubanMovieDetailData;

import io.reactivex.subjects.PublishSubject;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/29 0029.
 **/
public class DoubanMovieDetailViewModel extends BaseViewModel {

    public final ObservableField<DoubanMovieDetailData> mObservableField = new ObservableField<>();
    private DoubanMovieDataSource mDoubanMovieDataSource;

    public DoubanMovieDetailViewModel(@NonNull Application application, PublishSubject<Lifecycle.Event> publishSubject,
                                      DoubanMovieDataSource doubanMovieDataSource) {
        super(application, publishSubject);
        this.mDoubanMovieDataSource = doubanMovieDataSource;
    }

    public void loadData(String id){
        super.initData(mDoubanMovieDataSource.getDouBanMovieDetail(id));
    }

    @Override
    protected void onDataResponse(BaseResponse response) {
        mObservableField.set((DoubanMovieDetailData) response);
    }
}
