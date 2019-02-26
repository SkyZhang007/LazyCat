package com.sky.gank.toolbar;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.content.res.AppCompatResources;

import com.sky.gank.base.BaseViewModel;
import com.sky.gank.command.BindingCommand;
import com.sky.gank.command.BindingConsumer;

import io.reactivex.subjects.PublishSubject;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/2/15 0015.
 **/
public class ToolbarViewModel extends BaseViewModel {

    public final ObservableField<String> mTitle = new ObservableField<>();
    public final ObservableField<Drawable> mBackground = new ObservableField<>();
    public final ObservableField<Drawable> mNavButtonView = new ObservableField<>();
    public BindingCommand<Activity> mNavClickCommand;

    public ToolbarViewModel(@NonNull Application application, PublishSubject<Lifecycle.Event> publishSubject) {
        super(application, publishSubject);
    }

    public void initNavClick(){
        mNavClickCommand = new BindingCommand<>(new BindingConsumer<Activity>() {
            @Override
            public void call(Activity activity) {
                activity.onBackPressed();
            }
        });
    }

    public void setTitle(String titleText){
        mTitle.set(titleText);
    }

    public void setTitle(@StringRes int titleRes){
        mTitle.set(getApplication().getResources().getString(titleRes));
    }

    public void setBackground(Drawable drawable){
        mBackground.set(drawable);
    }

    public void setBackground(@DrawableRes int drawable){
        mBackground.set(AppCompatResources.getDrawable(getApplication(),drawable));
    }

    public void setNavButtonView(Drawable drawable){
        mNavButtonView.set(drawable);
    }

    public void setNavButtonView(@DrawableRes int drawable){
        if(0 == drawable){
            mNavButtonView.set(null);
        } else {
            mNavButtonView.set(AppCompatResources.getDrawable(getApplication(),drawable));
        }
    }


}
