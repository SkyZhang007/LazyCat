package com.sky.gank.net;


import com.sky.gank.data.douban.DoubanMovieData;
import com.sky.gank.data.douban.DoubanMovieDetailData;
import com.sky.gank.data.gank.GankData;
import com.sky.gank.data.meizi.MeiziData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/11 0011.
 **/
public interface RetrofitService {

    @GET("data/福利/{size}/{page}")
    Observable<MeiziData> getMeizhiData(@Path("page") int page, @Path("size") int size);

    @GET("day/{date}")
    Observable<GankData> getGankData(@Path("date") String date);

    //https://blog.csdn.net/izengjing/article/details/82963735#%E5%89%8D%E8%A8%80
    @GET("{type}")
    Observable<DoubanMovieData> getDouBanMovies(@Path("type") String type, @Query("start") int start, @Query("count") int count);

    @GET("subject/{id}")
    Observable<DoubanMovieDetailData> getDouBanMovieDetail(@Path("id") String id);

}
