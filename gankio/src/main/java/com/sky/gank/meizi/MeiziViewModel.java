package com.sky.gank.meizi;

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
import com.sky.gank.data.meizi.LocalMeiziDataSource;
import com.sky.gank.data.meizi.MeizhiBean;
import com.sky.gank.data.meizi.MeiziData;
import com.sky.gank.data.meizi.RemoteMeiziDataSource;
import com.sky.gank.util.LogUtils;

import io.reactivex.subjects.PublishSubject;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/31 0031.
 **/
public class MeiziViewModel extends BaseViewModel{

    // 数据源list
    public final ObservableList<MeiziItemViewModel> mObservableMeizi = new ObservableArrayList<>();
    // item binding
    public final ItemBinding<MeiziItemViewModel> mMeiziItem = ItemBinding.of(BR.meiziItem, R.layout.item_meizi);
    // adapter
    public final MyRecyclerViewAdapter<MeiziItemViewModel> mAdapter = new MyRecyclerViewAdapter<>();
    private int mLoadPage = 1;
    private int mLoadSize = 10;

    public MeiziViewModel(@NonNull final Application application, PublishSubject<Lifecycle.Event> publishSubject) {
        super(application, publishSubject);
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
            LogUtils.i(TAG_BASE_MODEL,"加载数据，item数量：" + i);
            if(i == mAdapter.getItemCount()){
                mLoadPage++;
                loadData();
            }
        }
    });

    public void loadData(){
        LogUtils.i(TAG_BASE_MODEL,"加载数据页数"+mLoadPage);
        super.initData(RemoteMeiziDataSource.getInstance().getMeizi(mLoadPage,mLoadSize),true);
    }

    @Override
    protected void initDataLocal() {
        super.initDataLocal();
        LogUtils.i(TAG_BASE_MODEL,"本地加载数据页数："+mLoadPage);
        super.initData(LocalMeiziDataSource.getInstance().getMeizi(mLoadPage,mLoadSize),false);
    }

    @Override
    protected void onDataResponse(BaseResponse response) {
        super.onDataResponse(response);
        updateList((MeiziData) response);
    }

    private void updateList(MeiziData data){
        if(mLoadPage == 1 && !mObservableMeizi.isEmpty()){
            mObservableMeizi.clear();
        }
        if(null != data && !data.getResults().isEmpty()){
            for (MeizhiBean meizhiBean:data.getResults()){
                MeiziItemViewModel meiziItemViewModel = new MeiziItemViewModel(MeiziViewModel.this,meizhiBean);
                mObservableMeizi.add(meiziItemViewModel);
            }
            if(mSaveData){
                LocalMeiziDataSource.getInstance().insertMeizi(data.getResults());
            }
        }
    }

}
