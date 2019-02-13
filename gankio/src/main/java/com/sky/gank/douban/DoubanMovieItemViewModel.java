package com.sky.gank.douban;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.sky.gank.R;
import com.sky.gank.BR;
import com.sky.gank.base.ItemViewModel;
import com.sky.gank.base.MyRecyclerViewAdapter;
import com.sky.gank.command.BindingCommand;
import com.sky.gank.command.BindingConsumer;
import com.sky.gank.data.douban.DoubanMovieData;
import com.sky.gank.data.douban.DoubanMovieStaff;
import com.sky.gank.util.ViewUtil;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/2 0002.
 **/
public class DoubanMovieItemViewModel extends ItemViewModel<DoubanMovieViewModel> {

    public DoubanMovieData.SubjectsBean bean;
    public Drawable drawableImg;
    public ObservableBoolean mExpandState = new ObservableBoolean();
    public final ObservableList<DoubanMovieActorItemViewModel> mActorItemList = new ObservableArrayList<>();
    public final ItemBinding<DoubanMovieActorItemViewModel> mActorItem = ItemBinding.of(BR.item, R.layout.item_douban_actor);
    public final MyRecyclerViewAdapter<DoubanMovieActorItemViewModel> mAdapter = new MyRecyclerViewAdapter<>();

    public DoubanMovieItemViewModel(@NonNull DoubanMovieViewModel viewModel, DoubanMovieData.SubjectsBean subjectsBean) {
        super(viewModel);
        this.bean = subjectsBean;
        //ImageView的占位图片，可以解决RecyclerView中图片错误问题
        drawableImg = ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.ic_launcher);
        mExpandState.set(false);
        initActor(subjectsBean);
    }

    private void initActor(DoubanMovieData.SubjectsBean subjectsBean) {
        for (DoubanMovieData.SubjectsBean.DirectorsBean directorsBean:subjectsBean.getDirectors()){
            DoubanMovieActorItemViewModel doubanMovieActorItemViewModel = new DoubanMovieActorItemViewModel(viewModel.getApplication(),
                    initMovieStaff(directorsBean.getName(),directorsBean.getAvatars().getSmall(),
                            directorsBean.getAvatars().getMedium(),directorsBean.getAvatars().getLarge(),"0"));
            mActorItemList.add(doubanMovieActorItemViewModel);
        }
        for (DoubanMovieData.SubjectsBean.CastsBean castsBean:subjectsBean.getCasts()){
            DoubanMovieActorItemViewModel doubanMovieActorItemViewModel = new DoubanMovieActorItemViewModel(viewModel.getApplication(),
                    initMovieStaff(castsBean.getName(),castsBean.getAvatars().getSmall(),
                            castsBean.getAvatars().getMedium(),castsBean.getAvatars().getLarge(),"1"));
            mActorItemList.add(doubanMovieActorItemViewModel);
        }
    }

    private DoubanMovieStaff initMovieStaff(String name,String smallImg,String mediumImg,String largeImg,String type){
        DoubanMovieStaff doubanMovieStaff = new DoubanMovieStaff();
        doubanMovieStaff.setName(name);
        doubanMovieStaff.setSmallImg(smallImg);
        doubanMovieStaff.setMediumImg(mediumImg);
        doubanMovieStaff.setLargeImg(largeImg);
        doubanMovieStaff.setType(type);
        return doubanMovieStaff;
    }

    public BindingCommand<View> mItemClick = new BindingCommand<>(new BindingConsumer<View>() {
        @Override
        public void call(View view) {
            Context currentActivity = ViewUtil.getActivityFromView(view);
            if(null == currentActivity){
                return;
            }
            DoubanMovieDetailActivity.goMovieDetail(currentActivity,bean.getId(),view);
        }
    });

    public BindingCommand<View> mExpandClick = new BindingCommand<>(new BindingConsumer<View>() {
        @Override
        public void call(View view) {
            mExpandState.set(!mExpandState.get());
        }
    });
}
