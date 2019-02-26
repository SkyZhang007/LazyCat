package com.sky.gank.base;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.gank.R;
import com.sky.gank.toolbar.ToolbarViewModel;
import com.sky.gank.BR;

import io.reactivex.subjects.PublishSubject;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/31 0031.
 **/
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel>
        extends Fragment{

    protected V mBinding;
    protected VM mViewModel;
    protected final PublishSubject<Lifecycle.Event> mLifecycleSubject = PublishSubject.create();
    protected ToolbarViewModel mToolbarViewModel;

    /**
     * 是否执行了lazyLoad方法
     */
    private boolean isLoaded;
    /**
     * 是否创建了View
     */
    private boolean isCreateView;

    /**
     * 当从另一个activity回到fragment所在的activity
     * 当fragment回调onResume方法的时候，可以通过这个变量判断fragment是否可见，来决定是否要刷新数据
     */
    public boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null == mBinding){
            mBinding = DataBindingUtil.inflate(inflater, initContentView(), container, getAttachToParent());
            mBinding.setVariable(initVariableId(), mViewModel = initViewModel());
            initToolbar();
            mBinding.executePendingBindings();

            isCreateView = true;
            onVisible();
        }
        return mBinding.getRoot();
    }

    /**
     * 初始化根布局
     * @return 布局layout的id
     */
    public abstract int initContentView();

    /**
     * 要想fragment依附于activity，请重写此方法，并返回true
     * （fragment xml根布局为，merge时）
     * @return
     */
    protected boolean getAttachToParent() {
        return false;
    }

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    private void initToolbar(){
        if(0 != initToolbarId()){
            mToolbarViewModel = new ToolbarViewModel(getApplication(),mLifecycleSubject);
            mBinding.setVariable(initToolbarId(),mToolbarViewModel);
            if(showBack()){
                mToolbarViewModel.setNavButtonView(R.drawable.ic_arrow_white_24dp);
                mToolbarViewModel.initNavClick();
            }
            setToolbar();
        }
    }

    protected boolean showBack(){
        return false;
    }

    /**
     * 实例化toolbar
     * @return toolbar VM id
     */
    protected int initToolbarId(){
        return BR.toolbar;
    }

    /**
     * 设置 Toolbar 标题
     * @param title
     */
    protected void setToolBarTitle(String title) {
        if (!TextUtils.isEmpty(title) && null != mToolbarViewModel) {
            mToolbarViewModel.setTitle(title);
        }
    }

    protected void setToolbar(){}

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public abstract VM initViewModel();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLifecycleSubject.onNext(Lifecycle.Event.ON_CREATE);
        initViewObservable();
        lazyLoad();
        commonLoad();
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleSubject.onNext(Lifecycle.Event.ON_START);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLifecycleSubject.onNext(Lifecycle.Event.ON_RESUME);
    }

    public abstract void initViewObservable();

    private void lazyLoad() {
        initData();
    }

    /**
     * fragment可见时才加载数据data
     */
    public abstract void initData();

    protected abstract void commonLoad();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    private void onVisible() {
        isVisible = true;

        if(isLoaded){
            refreshLoad();
        }
        if (!isLoaded && isCreateView && getUserVisibleHint()) {
            isLoaded = true;
            lazyLoad();
        }
    }

    /**
     * 在Fragment第一次可见加载以后，每次Fragment滑动可见的时候会回调这个方法，
     * 子类可以重写这个方法做数据刷新操作
     */
    protected void refreshLoad(){}

    private void onInvisible() {
        isVisible = false;
    }

    //刷新布局
    public void refreshLayout() {
        if (mViewModel != null) {
            mBinding.setVariable(initVariableId(), mViewModel);
        }
    }

    protected Application getApplication(){
        return getActivity().getApplication();
    }

    @Override
    public void onPause() {
        mLifecycleSubject.onNext(Lifecycle.Event.ON_PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        mLifecycleSubject.onNext(Lifecycle.Event.ON_STOP);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        mLifecycleSubject.onNext(Lifecycle.Event.ON_DESTROY);
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.onDestroy();
        }
        mViewModel = null;
    }

}