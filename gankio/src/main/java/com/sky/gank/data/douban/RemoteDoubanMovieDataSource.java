package com.sky.gank.data.douban;

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
public class RemoteDoubanMovieDataSource implements DoubanMovieDataSource{

    public static final String MOVETYPE_IN_THEATERS = "in_theaters";
    public static final String MOVETYPE_TOP250 = "top250";

    private static RemoteDoubanMovieDataSource sInstance;

    private RemoteDoubanMovieDataSource() {
    }

    public static RemoteDoubanMovieDataSource getInstance() {
        if (sInstance == null) {
            synchronized (RemoteDoubanMovieDataSource.class) {
                if (sInstance == null) {
                    sInstance = new RemoteDoubanMovieDataSource();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<DoubanMovieData> getDouBanMovies(String type,int start,int count) {
        return HttpUtil.getInstance()
                .setBaseUrl(Urls.DOUBAN_API_BASE)
                .create(RetrofitService.class)
                .getDouBanMovies(type,start,count);
    }

    @Override
    public Observable<DoubanMovieDetailData> getDouBanMovieDetail(String id) {
        return HttpUtil.getInstance()
                .setBaseUrl(Urls.DOUBAN_API_BASE)
                .create(RetrofitService.class)
                .getDouBanMovieDetail(id);
    }
}
