package com.sky.gank.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;

import com.sky.gank.net.HttpUtil;
import com.sky.gank.net.rxutil.DataCallback;
import com.sky.gank.util.LogUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/31 0031.
 **/
public abstract class BaseViewModel extends AndroidViewModel {

    public static final String TAG_BASE_MODEL = BaseViewModel.class.getSimpleName();
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    public final ObservableField<Boolean> mRefreshing = new ObservableField<>();
    private final PublishSubject<Lifecycle.Event> mLifeEvent;
    public final ObservableField<BaseToolbar> mToolbar = new ObservableField<>();

    public BaseViewModel(@NonNull Application application,PublishSubject<Lifecycle.Event> publishSubject) {
        super(application);
        mLifeEvent = publishSubject;
        mRefreshing.set(false);
    }

    protected void initData(Observable ob){
        if(mRefreshing.get()){
            return;
        }
        mRefreshing.set(true);
        HttpUtil.getInstance().toSubscribe(ob, Lifecycle.Event.ON_DESTROY
                ,mLifeEvent,new DataCallback<BaseResponse>() {
                    @Override
                    public void onErrors(Throwable e) {
                        LogUtils.e(LogUtils.TAG,e.getMessage());
                    }
                    @Override
                    public void onResponse(BaseResponse response) {
                        onDataResponse(response);
                        LogUtils.i(TAG_BASE_MODEL,response.toString());
                    }
                    @Override
                    public void addDispose(Disposable disposable) {
                        mDisposable.add(disposable);
                    }
                    @SuppressLint("CheckResult")
                    @Override
                    public void onComplete() {
                        Observable
                                .timer(1, TimeUnit.SECONDS)
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) {
                                        mRefreshing.set(false);
                                    }
                                });
                    }

                });
    }

    public void onDestroy(){
        mDisposable.clear();
        onCleared();
    }

    protected void onDataResponse(BaseResponse response){
    }

    protected void initToolbar(BaseToolbar baseToolbar){
        mToolbar.set(baseToolbar);
    }

}
