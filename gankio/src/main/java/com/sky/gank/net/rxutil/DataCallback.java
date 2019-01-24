package com.sky.gank.net.rxutil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/8 0008.
 **/
public abstract class DataCallback<T> implements Observer<T> {

    @Override
    public void onComplete() {
        onAfter();
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        onBefore(disposable);
    }

    @Override
    public void onNext(T t) {
        try {
            onResponse(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        onErrors(e);
    }

    public void onBefore(Disposable disposable){
        addDispose(disposable);
    }

    public void onAfter(){
    }

    public abstract void onErrors( Throwable e);

    public abstract void onResponse(T response);

    public abstract void addDispose(Disposable disposable);

}