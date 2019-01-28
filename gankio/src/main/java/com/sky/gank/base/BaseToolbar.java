package com.sky.gank.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;

/**
 * 设置 toolbar
 */
public class BaseToolbar {

    private String title;
    /**
     * 背景色值
     */
    private int backgroundRes;
    /**
     * 左侧导航键
     */
    private Drawable navigationIconRes;

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public void setBackgroundRes(int backgroundRes) {
        this.backgroundRes = backgroundRes;
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

    public static class Builder{

        private int titleRes;
        private int backgroundRes;
        private Drawable navigationIconRes;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitleRes(@StringRes int title) {
            this.titleRes = title;
            return this;
        }

        public Builder setBackgroundRes(@ColorRes int backgroundRes) {
            this.backgroundRes = backgroundRes;
            return this;
        }

        public Builder setNavigationIconRes(Drawable navigationIconRes) {
            this.navigationIconRes = navigationIconRes;
            return this;
        }

        public BaseToolbar build(){
            BaseToolbar baseToolbar = new BaseToolbar();
            baseToolbar.setBackgroundRes(backgroundRes);
            String title = context.getResources().getString(titleRes);
            if(!TextUtils.isEmpty(title)){
                baseToolbar.setTitle(title);
            }
            if(null != navigationIconRes){
                baseToolbar.setNavigationIconRes(navigationIconRes);
            }
            return baseToolbar;
        }

    }

}
