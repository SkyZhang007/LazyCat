package com.sky.gank.gank;

import android.content.Intent;

import com.sky.gank.BR;
import com.sky.gank.R;
import com.sky.gank.base.BaseAppCompatActivity;
import com.sky.gank.base.ViewModelFactory;
import com.sky.gank.data.gank.GankBean;
import com.sky.gank.data.gank.RemoteGankDataSource;
import com.sky.gank.databinding.ActivityGankBinding;
import com.sky.gank.util.DateUtil;

/**
 * 类名称：GankActivity
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/18 0018
 **/
public class GankActivity extends BaseAppCompatActivity<ActivityGankBinding,GankViewModel> {

    public static final String INTENT_GANK_BEAN = "gankBean";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_gank;
    }

    @Override
    protected int initVariableId() {
        return BR.gankViewModel;
    }

    @Override
    protected GankViewModel initViewModel() {
        return ViewModelFactory.getInstance(getApplication(),mLifecycleSubject,RemoteGankDataSource.getInstance())
                .createViewModel(this,GankViewModel.class);
    }

    @Override
    protected void getIntentData(Intent intent) {
        final GankBean gankBean = (GankBean) intent.getSerializableExtra(INTENT_GANK_BEAN);
        mViewModel.mBean.set(gankBean);
        mViewModel.loadData(DateUtil.formatDateStringToString(gankBean.getCreateData()));
    }

    @Override
    protected boolean showBack() {
        return true;
    }

}
