package com.sky.gank.data.meizi;

import com.sky.gank.base.BaseDataSource;

import io.reactivex.Observable;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/31 0031.
 **/
public interface MeiziDataSource extends BaseDataSource {

    Observable<MeiziData> getMeizi(int page, int size);

    void insertOrUpdateMeizi(MeiziData data);

    void deleteAllMeizhis();

}
