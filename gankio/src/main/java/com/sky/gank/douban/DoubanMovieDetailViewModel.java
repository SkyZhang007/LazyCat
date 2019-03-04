package com.sky.gank.douban;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sky.gank.BR;
import com.sky.gank.R;
import com.sky.gank.base.BaseResponse;
import com.sky.gank.base.BaseViewModel;
import com.sky.gank.base.MyRecyclerViewAdapter;
import com.sky.gank.data.douban.DoubanMovieDataSource;
import com.sky.gank.data.douban.DoubanMovieDetailData;
import com.sky.gank.data.douban.DoubanMovieStaff;
import com.sky.gank.data.douban.RemoteDoubanMovieDataSource;
import com.sky.gank.util.ViewUtil;

import io.reactivex.subjects.PublishSubject;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/29 0029.
 **/
public class DoubanMovieDetailViewModel extends BaseViewModel {

    public final ObservableField<DoubanMovieDetailData> mObservableField = new ObservableField<>();
    public final ObservableList<DoubanMovieActorItemViewModel> mActorItemList = new ObservableArrayList<>();
    public final ItemBinding<DoubanMovieActorItemViewModel> mActorItem = ItemBinding.of(BR.item, R.layout.item_douban_actor);
    public final MyRecyclerViewAdapter<DoubanMovieActorItemViewModel> mAdapter = new MyRecyclerViewAdapter<>();

    public DoubanMovieDetailViewModel(@NonNull Application application, PublishSubject<Lifecycle.Event> publishSubject) {
        super(application, publishSubject);
        initToolbar(true);
    }

    public void loadData(String id){
        super.initData(RemoteDoubanMovieDataSource.getInstance().getDouBanMovieDetail(id),true);
    }

    @Override
    protected void onDataResponse(BaseResponse response) {
        DoubanMovieDetailData detailData = (DoubanMovieDetailData) response;
        mObservableField.set(detailData);
        initActor(detailData);
    }

    private void initActor(DoubanMovieDetailData detailData) {
        for (DoubanMovieDetailData.DirectorsBean directorsBean:detailData.getDirectors()){
            DoubanMovieActorItemViewModel doubanMovieActorItemViewModel = new DoubanMovieActorItemViewModel(getApplication(),
                    initMovieStaff(directorsBean.getName(),directorsBean.getAvatars().getSmall(),
                            directorsBean.getAvatars().getMedium(),directorsBean.getAvatars().getLarge(),"0"));
            mActorItemList.add(doubanMovieActorItemViewModel);
        }
        for (DoubanMovieDetailData.CastsBean castsBean:detailData.getCasts()){
            DoubanMovieActorItemViewModel doubanMovieActorItemViewModel = new DoubanMovieActorItemViewModel(getApplication(),
                    initMovieStaff(castsBean.getName(),castsBean.getAvatars().getSmall(),
                            castsBean.getAvatars().getMedium(),castsBean.getAvatars().getLarge(),"1"));
            mActorItemList.add(doubanMovieActorItemViewModel);
        }
    }

    private DoubanMovieStaff initMovieStaff(String name, String smallImg, String mediumImg, String largeImg, String type){
        DoubanMovieStaff doubanMovieStaff = new DoubanMovieStaff();
        doubanMovieStaff.setName(name);
        doubanMovieStaff.setSmallImg(smallImg);
        doubanMovieStaff.setMediumImg(mediumImg);
        doubanMovieStaff.setLargeImg(largeImg);
        doubanMovieStaff.setType(type);
        return doubanMovieStaff;
    }

}
