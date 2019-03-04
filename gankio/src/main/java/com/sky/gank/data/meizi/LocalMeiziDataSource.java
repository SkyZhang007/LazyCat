package com.sky.gank.data.meizi;

import com.sky.gank.base.BaseApplication;
import com.sky.gank.data.douban.DoubanMovieData;
import com.sky.gank.data.douban.DoubanMovieDataSource;
import com.sky.gank.data.douban.DoubanMovieDetailData;
import com.sky.gank.data.douban.SubjectsBean;
import com.sky.gank.greendao.MeizhiBeanDao;

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
public class LocalMeiziDataSource implements MeiziDataSource{

    private static LocalMeiziDataSource sInstance;

    private LocalMeiziDataSource() {
    }

    public static LocalMeiziDataSource getInstance() {
        if (sInstance == null) {
            synchronized (LocalMeiziDataSource.class) {
                if (sInstance == null) {
                    sInstance = new LocalMeiziDataSource();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<MeiziData> getMeizi(final int page, final int size) {
        return Observable.create(new ObservableOnSubscribe<MeiziData>() {
            @Override
            public void subscribe(ObservableEmitter<MeiziData> emitter) throws Exception {
                List<MeizhiBean> meizhiBeanList = loadMeiziFromDB(page,size);
                if(null != meizhiBeanList && !meizhiBeanList.isEmpty()){
                    MeiziData data = new MeiziData();
                    data.setResults(meizhiBeanList);
                    emitter.onNext(data);
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    public void insertMeizi(List<MeizhiBean> meizhiBeans) {
        if(null != meizhiBeans && !meizhiBeans.isEmpty()){
            MeizhiBeanDao meizhiBeanDao = BaseApplication.getDaoSession().getMeizhiBeanDao();
            meizhiBeanDao.insertInTx(meizhiBeans);
        }
    }

    private static List<MeizhiBean> loadMeiziFromDB(int page,int size){
        int offset = (page-1)*size;
        if(offset < 0){
            offset = 0;
        }
        QueryBuilder<MeizhiBean> builder = BaseApplication.getDaoSession().getMeizhiBeanDao().queryBuilder();
        builder.limit(size);
        builder.offset(offset);
        return builder.list();
    }

}
