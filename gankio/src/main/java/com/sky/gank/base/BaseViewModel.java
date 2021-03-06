package com.sky.gank.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sky.gank.R;
import com.sky.gank.net.HttpUtil;
import com.sky.gank.net.rxutil.DataCallback;
import com.sky.gank.util.LogUtils;
import com.sky.gank.util.ViewUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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
    public final ObservableField<Toolbar> mToolbar = new ObservableField<>();
    // 是否更新数据库
    protected boolean mSaveData = false;

    public BaseViewModel(@NonNull Application application,PublishSubject<Lifecycle.Event> publishSubject) {
        super(application);
        mLifeEvent = publishSubject;
        mRefreshing.set(false);
    }

    protected void initData(Observable ob,boolean save){
        mSaveData = save;
        if(mRefreshing.get()){
            return;
        }
        mRefreshing.set(true);
        HttpUtil.getInstance().toSubscribe(ob, Lifecycle.Event.ON_DESTROY
                ,mLifeEvent,new DataCallback<BaseResponse>() {
                    @Override
                    public void onErrors(Throwable e) {
                        LogUtils.e(LogUtils.TAG,e.getMessage());
                        mRefreshing.set(false);
                        initDataLocal();
                    }
                    @Override
                    public void onResponse(final BaseResponse response) {
                        onDataResponse(response);
                        if(mSaveData){
                            mDisposable.add(Observable.create(new ObservableOnSubscribe<String>() {
                                @Override
                                public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                    saveDateLocalAsync(response);
                                    emitter.onComplete();
                                }
                            })
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(Schedulers.io())
                                    .subscribe(new Consumer<String>() {
                                        @Override
                                        public void accept(String s) throws Exception {

                                        }
                                    }));
                        }
                    }
                    @Override
                    public void addDispose(Disposable disposable) {
                        mDisposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                        hideFresh();
                    }
                });
    }

    private void hideFresh(){
        mDisposable.add(Observable
                .timer(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        mRefreshing.set(false);
                    }
                }));
    }

    protected void initDataLocal(){
    }

    protected void saveDateLocalAsync(BaseResponse baseResponse){
    }

    public void onDestroy(){
        mDisposable.clear();
        onCleared();
    }

    protected void onDataResponse(BaseResponse response){
    }

    protected void initToolbar(boolean showBack){
        Toolbar toolbar = new Toolbar(getApplication());
        if(showBack){
            toolbar.setNavigationIcon(R.drawable.ic_arrow_white_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewUtil.getActivityFromView(v).onBackPressed();
                }
            });
        } else {
            toolbar.setNavigationIcon(null);
        }
        toolbar.setTitle("Test");
        toolbar.setBackground(null);
        mToolbar.set(toolbar);
    }

}
