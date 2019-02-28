package com.sky.gank.base;

import android.app.Application;

import com.sky.gank.greendao.DaoMaster;
import com.sky.gank.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/2/28 0028.
 **/
public class BaseApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "gank-db");
        Database db = helper.getWritableDb();

        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
