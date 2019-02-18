package com.sky.gank.info;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.support.annotation.NonNull;

import com.sky.gank.base.BaseViewModel;

import io.reactivex.subjects.PublishSubject;

public class InfoViewModel extends BaseViewModel {

    public InfoViewModel(@NonNull Application application, PublishSubject<Lifecycle.Event> publishSubject) {
        super(application, publishSubject);
    }
}
