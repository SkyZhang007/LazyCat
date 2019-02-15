package com.sky.gank.toolbar;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.sky.gank.base.BaseViewModel;

import io.reactivex.subjects.PublishSubject;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/2/15 0015.
 **/
public class ToolbarViewModel extends BaseViewModel {

    public final ObservableField<String> mTitle = new ObservableField<>();

    public ToolbarViewModel(@NonNull Application application, PublishSubject<Lifecycle.Event> publishSubject) {
        super(application, publishSubject);
    }

    public void setTitle(String titleText){
        mTitle.set(titleText);
    }

}
