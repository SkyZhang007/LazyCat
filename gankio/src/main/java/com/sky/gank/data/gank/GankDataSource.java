package com.sky.gank.data.gank;

import com.sky.gank.base.BaseDataSource;

import io.reactivex.Observable;

/**
 * 类名称：GankDataSource
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/22 0022
 **/
public interface GankDataSource extends BaseDataSource {

    Observable<GankData> getGankData(String date);

}
