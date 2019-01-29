package com.sky.gank.viewadapters.toolbar;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sky.gank.command.BindingCommand;
import com.sky.gank.util.ViewUtil;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/29 0029.
 **/
public class ViewAdapter {

    @BindingAdapter(value={"onNavClickCommand"},requireAll=false)
    public static void onToolbarNavClick(Toolbar toolbar, final BindingCommand<Activity> onNavClickCommand){
        if(null != onNavClickCommand){
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavClickCommand.execute(ViewUtil.getActivityFromView(v));
                }
            });
        }
    }

}
