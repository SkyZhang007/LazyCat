package com.sky.gank.douban;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.sky.gank.BR;
import com.sky.gank.R;
import com.sky.gank.base.BaseResponse;
import com.sky.gank.base.BaseViewModel;
import com.sky.gank.base.MyRecyclerViewAdapter;
import com.sky.gank.command.BindingAction;
import com.sky.gank.command.BindingCommand;
import com.sky.gank.command.BindingConsumer;
import com.sky.gank.data.douban.DoubanMovieData;
import com.sky.gank.data.douban.DoubanMovieDataSource;
import com.sky.gank.data.douban.RemoteDoubanMovieDataSource;
import com.sky.gank.util.LogUtils;

import io.reactivex.subjects.PublishSubject;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/16 0016.
 **/
public class DoubanMovieViewModel extends BaseViewModel {

    // 数据源list
    public final ObservableList<DoubanMovieItemViewModel> mObservableList = new ObservableArrayList<>();
    // adapter
    public final MyRecyclerViewAdapter<DoubanMovieItemViewModel> mAdapter = new MyRecyclerViewAdapter<>();
    // item binding
    public final ItemBinding<DoubanMovieItemViewModel> mItem = ItemBinding.of(BR.item, R.layout.item_douban_movie);
    private int mLoadPage = 1;
    private int mLoadSize = 20;
    private DoubanMovieDataSource mDoubanMovieDataSource;

    public DoubanMovieViewModel(@NonNull Application application, PublishSubject<Lifecycle.Event> publishSubject,
                                DoubanMovieDataSource doubanMovieDataSource) {
        super(application, publishSubject);
        this.mDoubanMovieDataSource = doubanMovieDataSource;
    }

    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            mLoadPage = 1;
            loadData();
        }
    });

    public BindingCommand<Integer> loadMore = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer i) {
            LogUtils.i(TAG_BASE_MODEL, "加载数据，item数量：" + i);
            if (i == mAdapter.getItemCount()) {
                mLoadPage++;
                loadData();
            }
        }
    });

    public void loadData() {
        LogUtils.i(TAG_BASE_MODEL, "加载数据页数");
        super.initData(mDoubanMovieDataSource
                .getDouBanMovies(RemoteDoubanMovieDataSource.MOVETYPE_IN_THEATERS, mLoadPage, mLoadSize));
    }

    @Override
    protected void onDataResponse(BaseResponse response) {
        super.onDataResponse(response);
        updateList((DoubanMovieData) response);
    }

    private void updateList(DoubanMovieData data) {
        if (mLoadPage == 1 && !mObservableList.isEmpty()) {
            mObservableList.clear();
        }
        if (null != data && !data.getSubjects().isEmpty()) {
            for (DoubanMovieData.SubjectsBean subjectsBean : data.getSubjects()) {
                DoubanMovieItemViewModel viewModel = new DoubanMovieItemViewModel(DoubanMovieViewModel.this, subjectsBean);
                mObservableList.add(viewModel);
            }
        }
    }

}
