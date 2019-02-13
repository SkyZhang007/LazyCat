package com.sky.gank.meizi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.sky.gank.R;
import com.sky.gank.base.ItemViewModel;
import com.sky.gank.command.BindingCommand;
import com.sky.gank.command.BindingConsumer;
import com.sky.gank.data.gank.GankBean;
import com.sky.gank.data.meizi.MeiziData;
import com.sky.gank.gank.GankActivity;
import com.sky.gank.util.VersionUtil;
import com.sky.gank.util.ViewUtil;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/2 0002.
 **/
public class MeiziItemViewModel extends ItemViewModel<MeiziViewModel> {

    public MeiziData.MeizhiBean bean;
    public Drawable drawableImg;

    public MeiziItemViewModel(@NonNull MeiziViewModel viewModel,MeiziData.MeizhiBean meizhiBean) {
        super(viewModel);
        this.bean = meizhiBean;
        //ImageView的占位图片，可以解决RecyclerView中图片错误问题
        drawableImg = ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.ic_launcher);
    }

    public BindingCommand<View> mItemClick = new BindingCommand<>(new BindingConsumer<View>() {
        @Override
        public void call(View view) {
            Context currentActivity = ViewUtil.getActivityFromView(view);
            if(null == currentActivity){
                return;
            }
            Intent intent = new Intent(currentActivity,GankActivity.class);
            //创建传输bean
            GankBean gankBean = new GankBean();
            gankBean.setImageUrl(bean.getUrl());
            gankBean.setCreateData(bean.getCreatedAt());
            intent.putExtra(GankActivity.INTENT_GANK_BEAN,gankBean);
            if (VersionUtil.hasLollipop()) {
                startActivityLollipop(currentActivity, view, intent);
            } else {
                startActivityGingerBread((Activity) currentActivity, view, intent);
            }
        }
    });

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startActivityLollipop(Context context,View view, Intent intent) {
        // 避免父容器转换
        ((ViewGroup)view.getParent()).setTransitionGroup(false);
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation((Activity) context, view, context.getResources().getString(R.string.meizi_transition_detail));
        context.startActivity(intent, options.toBundle());
    }

    private void startActivityGingerBread(Activity context,View view, Intent intent) {
        int[] screenLocation = new int[2];
        view.getLocationOnScreen(screenLocation);
        intent.
                putExtra("left", screenLocation[0]).
                putExtra("top", screenLocation[1]).
                putExtra("width", view.getWidth()).
                putExtra("height", view.getHeight());

        context.startActivity(intent);

        // Override transitions: we don't want the normal window animation in addition to our
        // custom one
        context.overridePendingTransition(0, 0);

        // The detail activity handles the enter and exit animations. Both animations involve a
        // ghost view animating into its final or initial position respectively. Since the detail
        // activity starts translucent, the clicked view needs to be invisible in order for the
        // animation to look correct.
        // compile 'com.nineoldandroids:library:2.4.0'
//        ViewPropertyAnimator.animate(view).alpha(0.0f);
    }

}
