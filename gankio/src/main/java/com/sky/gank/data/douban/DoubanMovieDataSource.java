package com.sky.gank.data.douban;

import com.sky.gank.base.BaseDataSource;

import io.reactivex.Observable;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/16 0016.
 **/
public interface DoubanMovieDataSource extends BaseDataSource {

    Observable<DoubanMovieData> getDouBanMovies(String type,int start,int count);

}
