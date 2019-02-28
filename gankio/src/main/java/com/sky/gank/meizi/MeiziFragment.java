package com.sky.gank.meizi;

import com.sky.gank.BR;
import com.sky.gank.R;
import com.sky.gank.base.BaseFragment;
import com.sky.gank.base.ViewModelFactory;
import com.sky.gank.data.meizi.RemoteMeiziDataSource;
import com.sky.gank.databinding.FragmentMeiziBinding;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/31 0031.
 **/
public class MeiziFragment extends BaseFragment<FragmentMeiziBinding,MeiziViewModel>{

    public MeiziFragment() {
    }

    public static MeiziFragment newInstance() {
        return new MeiziFragment();
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_meizi;
    }

    @Override
    public int initVariableId() {
        return BR.meiziViewModel;
    }

    @Override
    public MeiziViewModel initViewModel() {
        return ViewModelFactory.getInstance(getApplication(),mLifecycleSubject,RemoteMeiziDataSource.getInstance())
                .createViewModel(getActivity(),MeiziViewModel.class);
    }

    @Override
    public void initViewObservable() {
        mToolbarViewModel.setTitle(R.string.title_meizi);
        mToolbarViewModel.setBackground(R.color.colorPrimary);
    }

    @Override
    public void initData() {
        mViewModel.loadData();
    }

}
