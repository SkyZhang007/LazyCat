package com.sky.gank.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.view.View;

import com.sky.gank.command.BindingCommand;

/**
 * 设置 toolbar
 */
public class BaseToolbar {

    private String title;
    /**
     * 背景色值
     */
    private Drawable backgroundDrawableRes;
    /**
     * 左侧导航键
     */
    private Drawable navigationIconRes;
    /**
     * 导航键点击
     */
    private BindingCommand<Activity> onNavClickCommand;

    public Drawable getBackgroundDrawableRes() {
        return backgroundDrawableRes;
    }

    public void setBackgroundDrawableRes(Drawable backgroundDrawableRes) {
        this.backgroundDrawableRes = backgroundDrawableRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getNavigationIconRes() {
        return navigationIconRes;
    }

    public void setNavigationIconRes(Drawable navigationIconRes) {
        this.navigationIconRes = navigationIconRes;
    }

    public BindingCommand getOnNavClickCommand() {
        return onNavClickCommand;
    }

    public void setOnNavClickCommand(BindingCommand<Activity> onNavClickCommand) {
        this.onNavClickCommand = onNavClickCommand;
    }

    public static class Builder{

        private String title;
        private Drawable navigationIconRes;
        private Context context;
        private Drawable backgroundDrawableRes;
        private BindingCommand<Activity> onNavClickCommand;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitleRes(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitleRes(int titleRes) {
            this.title = context.getString(titleRes);
            return this;
        }

        public Builder setBackgroundDrawableRes(int backgroundColorRes) {
            this.backgroundDrawableRes = AppCompatResources.getDrawable(context,backgroundColorRes);
            return this;
        }

        public Builder setBackgroundDrawableRes(Drawable backgroundDrawableResRes) {
            this.backgroundDrawableRes = backgroundDrawableResRes;
            return this;
        }

        public Builder setNavigationIconRes(@DrawableRes int drawableRes) {
            this.navigationIconRes = AppCompatResources.getDrawable(context,drawableRes);
            return this;
        }

        public Builder setNavigationIconRes(Drawable drawableRes) {
            this.navigationIconRes = drawableRes;
            return this;
        }

        public Builder setNavClickCommand(BindingCommand<Activity> bindingCommand) {
            this.onNavClickCommand = bindingCommand;
            return this;
        }

        public BaseToolbar build(){
            BaseToolbar baseToolbar = new BaseToolbar();
            if(null != backgroundDrawableRes){
                baseToolbar.setBackgroundDrawableRes(backgroundDrawableRes);
            }
            if(!TextUtils.isEmpty(title)){
                baseToolbar.setTitle(title);
            }
            if(null != navigationIconRes){
                baseToolbar.setNavigationIconRes(navigationIconRes);
            }
            if(null != onNavClickCommand){
                baseToolbar.setOnNavClickCommand(onNavClickCommand);
            }
            return baseToolbar;
        }

    }

}
