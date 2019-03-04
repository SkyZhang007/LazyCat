package com.sky.gank.base;

import android.app.Application;

import com.sky.gank.greendao.DaoMaster;
import com.sky.gank.greendao.DaoSession;
import com.sky.gank.greendao.DbManager;

import org.greenrobot.greendao.database.Database;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/2/28 0028.
 **/
public class BaseApplication extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        daoSession = DbManager.getDaoSession(this);
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
