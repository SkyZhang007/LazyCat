package com.sky.gank.data.douban;

import com.sky.gank.base.BaseApplication;
import com.sky.gank.greendao.SubjectsBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

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
    public Observable<DoubanMovieData> getDouBanMovies(String type, final int start, final int count) {
        return Observable.create(new ObservableOnSubscribe<DoubanMovieData>() {
            @Override
            public void subscribe(ObservableEmitter<DoubanMovieData> emitter) throws Exception {
                List<SubjectsBean> subjectsBeanList = loadDataFromDB(start,count);
                if(null != subjectsBeanList && !subjectsBeanList.isEmpty()){
                    DoubanMovieData data = new DoubanMovieData();
                    data.setSubjects(subjectsBeanList);
                    emitter.onNext(data);
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<DoubanMovieDetailData> getDouBanMovieDetail(String id) {
        return null;
    }

    public void insertOrUpdateData(List<SubjectsBean> subjectsBeans) {
        if(null != subjectsBeans && !subjectsBeans.isEmpty()){
            SubjectsBeanDao meizhiBeanDao = BaseApplication.getDaoSession().getSubjectsBeanDao();
            meizhiBeanDao.insertOrReplaceInTx(subjectsBeans);
        }
    }

    private static List<SubjectsBean> loadDataFromDB(int start,int count){
        int offset = (start-1)*count;
        if(offset < 0){
            offset = 0;
        }
        QueryBuilder<SubjectsBean> builder = BaseApplication.getDaoSession().getSubjectsBeanDao().queryBuilder();
        builder.limit(count);
        builder.offset(offset);
        return builder.list();
    }

}
