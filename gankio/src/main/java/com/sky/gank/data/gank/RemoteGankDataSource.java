package com.sky.gank.data.gank;

import com.sky.gank.net.HttpUtil;
import com.sky.gank.net.RetrofitService;
import com.sky.gank.net.Urls;

import io.reactivex.Observable;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/31 0031.
 **/
public class RemoteGankDataSource implements GankDataSource{

    private static RemoteGankDataSource sInstance;

    private RemoteGankDataSource() {
    }

    public static RemoteGankDataSource getInstance() {
        if (sInstance == null) {
            synchronized (RemoteGankDataSource.class) {
                if (sInstance == null) {
                    sInstance = new RemoteGankDataSource();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<GankData> getGankData(String data) {
        return HttpUtil.getInstance()
                .setBaseUrl(Urls.GANK_API_BASE)
                .create(RetrofitService.class)
                .getGankData(data);
    }
}
