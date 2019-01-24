package com.sky.gank.data.meizi;

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
public class RemoteMeiziDataSource implements MeiziDataSource{

    private static RemoteMeiziDataSource sInstance;

    private RemoteMeiziDataSource() {
    }

    public static RemoteMeiziDataSource getInstance() {
        if (sInstance == null) {
            synchronized (RemoteMeiziDataSource.class) {
                if (sInstance == null) {
                    sInstance = new RemoteMeiziDataSource();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<MeiziData> getMeizi(int page, int size) {
        return HttpUtil.getInstance()
                .setBaseUrl(Urls.GANK_API_BASE)
                .create(RetrofitService.class)
                .getMeizhiData(page,size);
    }

    @Override
    public void insertOrUpdateMeizi(MeiziData user) {
    }

    @Override
    public void deleteAllMeizhis() {
    }
}
