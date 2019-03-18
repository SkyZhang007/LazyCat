package com.sky.gank.base;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.sky.gank.BR;
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
    protected V mBinding;
    protected VM mViewModel;
    public final PublishSubject<Lifecycle.Event> mLifecycleSubject = PublishSubject.create();
    protected ToolbarViewModel mToolbarViewModel;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
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
            initToolbar();
        }
    }

    private void initToolbar(){
        if(0 != initToolbarId()){
            mToolbarViewModel = new ToolbarViewModel(getApplication(),mLifecycleSubject);
            mBinding.setVariable(initToolbarId(),mToolbarViewModel);
            setToolBarTitle(getTitle().toString());
            if(showBack()){
                mToolbarViewModel.setNavButtonView(R.drawable.ic_arrow_white_24dp);
                mToolbarViewModel.initNavClick();
            }
            setToolbar();
        }
    }

    protected void setToolbar(){}

    /**
     * 设置 Toolbar 标题
     * @param title
     */
    protected void setToolBarTitle(String title) {
        if (!TextUtils.isEmpty(title) && null != mToolbarViewModel) {
            mToolbarViewModel.setTitle(title);
        }
    }

    protected void setToolBarBackground(@ColorRes int colorDrawable) {
        if(null != mToolbarViewModel){
            mToolbarViewModel.setBackground(colorDrawable);
        }
    }

    protected void setToolbarNav(int navDrawable){
        if(null != mToolbarViewModel){
            mToolbarViewModel.setNavButtonView(navDrawable);
        }
    }

    protected boolean showBack(){
        return false;
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
        return BR.toolbar;
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
     * 获取 toolbar 对象
     */
    protected Toolbar getToolbar() {
        return mToolbar;
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
