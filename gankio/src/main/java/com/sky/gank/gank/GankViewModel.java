package com.sky.gank.gank;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.sky.gank.R;
import com.sky.gank.BR;
import com.sky.gank.base.BaseResponse;
import com.sky.gank.base.BaseToolbar;
import com.sky.gank.base.BaseViewModel;
import com.sky.gank.base.MyRecyclerViewAdapter;
import com.sky.gank.command.BindingAction;
import com.sky.gank.command.BindingCommand;
import com.sky.gank.command.BindingConsumer;
import com.sky.gank.data.gank.GankBean;
import com.sky.gank.data.gank.GankData;
import com.sky.gank.data.gank.GankDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * 类名称：GankViewModel
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/18 0018
 **/
public class GankViewModel extends BaseViewModel {

    public final ObservableField<GankBean> mBean = new ObservableField<>();
    public Drawable drawableImg;
    public final MyRecyclerViewAdapter<GankItemViewModel> mAdapter = new MyRecyclerViewAdapter<>();
    public final ObservableList<GankItemViewModel> mObservableList = new ObservableArrayList<>();
    private GankDataSource mGankDataSource;
    private String mCurrentData;

    public final ItemBinding<GankItemViewModel> mItemBinding = ItemBinding.of(new OnItemBind<GankItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, GankItemViewModel item) {
            if (!TextUtils.isEmpty(item.mGank.getTitleType())) {
                //设置头布局
                itemBinding.set(BR.gankTitle, R.layout.item_gank_title);
            } else {
                itemBinding.set(BR.itemGank, R.layout.item_gank);
            }
        }
    });

    public GankViewModel(@NonNull Application application, PublishSubject<Lifecycle.Event> publishSubject,
                         GankDataSource gankDataSource) {
        super(application, publishSubject);
        this.mGankDataSource = gankDataSource;
        drawableImg = ContextCompat.getDrawable(application, R.mipmap.ic_launcher);
        initToolbar(new BaseToolbar.Builder(application.getBaseContext())
                .setNavigationIconRes(R.drawable.ic_arrow_white_24dp)
                .setNavClickCommand(new BindingCommand<>(new BindingConsumer<Activity>() {
                    @Override
                    public void call(Activity activity) {
                        activity.onBackPressed();
                    }
                }))
                .build());
    }

    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            loadData(mCurrentData);
        }
    });

    public void loadData(String data){
        mCurrentData = data;
        super.initData(mGankDataSource.getGankData(data));
    }

    @Override
    protected void onDataResponse(BaseResponse response) {
        updateGankData((GankData)response);
    }

    private void updateGankData(GankData gankData) {
        if(!mObservableList.isEmpty()){
            mObservableList.clear();
        }
        List<GankData.Gank> list = new ArrayList<>();
        list.add(new GankData.Gank("Android"));
        list.addAll(gankData.getResults().getAndroidList());
        list.add(new GankData.Gank("iOS"));
        list.addAll(gankData.getResults().getiOSList());
        list.add(new GankData.Gank("休息视频"));
        list.addAll(gankData.getResults().getVideoList());
//        list.add(new GankData.Gank("福利"));
//        list.addAll(gankData.getResults().getMeiziList());
        list.add(new GankData.Gank("拓展资源"));
        list.addAll(gankData.getResults().getResourcesList());
        list.add(new GankData.Gank("瞎推荐"));
        list.addAll(gankData.getResults().getRecommendList());
        list.add(new GankData.Gank("App"));
        list.addAll(gankData.getResults().getAppList());

        for (GankData.Gank gank:list){
            GankItemViewModel gankItemViewModel = new GankItemViewModel(this, gank);
            mObservableList.add(gankItemViewModel);
        }
    }
}
