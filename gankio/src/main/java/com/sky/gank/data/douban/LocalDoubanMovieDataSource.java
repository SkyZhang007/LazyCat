package com.sky.gank.data.douban;

import com.sky.gank.base.BaseApplication;
import com.sky.gank.greendao.SubjectsBeanDao;
import com.sky.gank.net.HttpUtil;
import com.sky.gank.net.RetrofitService;
import com.sky.gank.net.Urls;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/31 0031.
 **/
public class LocalDoubanMovieDataSource implements DoubanMovieDataSource{

    public static final String MOVETYPE_IN_THEATERS = "in_theaters";
    public static final String MOVETYPE_TOP250 = "top250";
    private static LocalDoubanMovieDataSource sInstance;

    private LocalDoubanMovieDataSource() {
    }

    public static LocalDoubanMovieDataSource getInstance() {
        if (sInstance == null) {
            synchronized (LocalDoubanMovieDataSource.class) {
                if (sInstance == null) {
                    sInstance = new LocalDoubanMovieDataSource();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<DoubanMovieData> getDouBanMovies(String type,int start,int count) {
        return Observable.create(new ObservableOnSubscribe<DoubanMovieData>() {
            @Override
            public void subscribe(ObservableEmitter<DoubanMovieData> emitter) throws Exception {
                SubjectsBeanDao subjectsBeanDao = BaseApplication.getDaoSession().getSubjectsBeanDao();
                List<SubjectsBean> subjectsBeanList = subjectsBeanDao.loadAll();
                if(null != subjectsBeanList && !subjectsBeanList.isEmpty()){
                    DoubanMovieData data = new DoubanMovieData();
                    data.setSubjects(subjectsBeanList);
                    emitter.onNext(data);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<DoubanMovieDetailData> getDouBanMovieDetail(String id) {
        return HttpUtil.getInstance()
                .setBaseUrl(Urls.DOUBAN_API_BASE)
                .create(RetrofitService.class)
                .getDouBanMovieDetail(id);
    }
}
