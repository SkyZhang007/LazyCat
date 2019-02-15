package com.sky.gank.base;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sky.gank.R;
import com.sky.gank.toolbar.ToolbarViewModel;
import com.sky.gank.util.LogUtils;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Sky on 2018/12/10.
 */

public abstract class BaseAppCompatActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends AppCompatActivity {

    protected static final String BASE_TAG = BaseAppCompatActivity.class.getSimpleName();
    protected Toolbar mToolbar;
    private TextView mToolbarTitle;
    protected V mBinding;
    protected VM mViewModel;
    public final PublishSubject<Lifecycle.Event> mLifecycleSubject = PublishSubject.create();
    protected ToolbarViewModel mToolbarViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleSubject.onNext(Lifecycle.Event.ON_CREATE);
        LogUtils.i(BASE_TAG,getClass().getSimpleName());
        initViewDataBinding();
        if(null != getIntent()){
            getIntentData(getIntent());
        }
        initData();
    }

    protected void initViewDataBinding(){
        mBinding = DataBindingUtil.setContentView(this,getLayoutResId());
        if(null != mBinding){
            mBinding.setVariable(initVariableId(), mViewModel = initViewModel());
            setToolbar();
        }
    }

    protected void setToolbar(){
        if(0 != initToolbarId()){
            mToolbarViewModel = new ToolbarViewModel(getApplication(),mLifecycleSubject);
            mBinding.setVariable(initToolbarId(),mToolbarViewModel);
        }
    }

    protected void getIntentData(Intent intent) {
    }

    protected void initData(){

    }

    /**
     * 设置布局资源
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * 初始化ViewModel的id
     * @return BR的id(根据对应xml文件 < data > < variable > name < / variable > < / data > 自动生成)
     * 使用: BR.xx
     */
    protected abstract int initVariableId();

    /**
     * 重写此方法以实例化toolbar
     * @return toolbar VM id
     */
    protected int initToolbarId(){
        return 0;
    }
    /**
     * 初始化ViewModel
     * @return 继承BaseViewModel的ViewModel
     */
    protected abstract VM initViewModel();

    @Override
    protected void onStart() {
        mLifecycleSubject.onNext(Lifecycle.Event.ON_START);
        super.onStart();
    }

    @Override
    protected void onResume() {
        mLifecycleSubject.onNext(Lifecycle.Event.ON_RESUME);
        super.onResume();
    }

    //刷新布局
    public void refreshLayout() {
        if (mViewModel != null) {
            mBinding.setVariable(initVariableId(), mViewModel);
            mBinding.executePendingBindings();
        }
    }

    protected void setMenu(int menu){
        if(null != mToolbar){
            mToolbar.inflateMenu(menu);
        }
    }

    /**
     * 设置 Toolbar 标题
     * @param title
     */
    protected void setToolBarTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title) && null != mToolbarTitle) {
            mToolbarTitle.setText(title);
        }
    }

    /**
     * 获取 toolbar 对象
     */
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 覆写此方法设置是否展示后退按钮
     */
    protected boolean isShowBacking() {
        return false;
    }

    @Override
    protected void onPause() {
        mLifecycleSubject.onNext(Lifecycle.Event.ON_PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        mLifecycleSubject.onNext(Lifecycle.Event.ON_STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mLifecycleSubject.onNext(Lifecycle.Event.ON_DESTROY);
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.onDestroy();
        }
        mViewModel = null;
    }
}
