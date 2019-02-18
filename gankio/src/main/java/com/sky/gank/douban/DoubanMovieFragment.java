package com.sky.gank.douban;

import com.sky.gank.BR;
import com.sky.gank.R;
import com.sky.gank.base.BaseFragment;
import com.sky.gank.base.ViewModelFactory;
import com.sky.gank.data.douban.RemoteDoubanMovieDataSource;
import com.sky.gank.databinding.FragmentDoubanMovieBinding;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/16 0016.
 **/
public class DoubanMovieFragment extends BaseFragment<FragmentDoubanMovieBinding,DoubanMovieViewModel>{

    public DoubanMovieFragment() {
    }

    public static DoubanMovieFragment newInstance() {
        return new DoubanMovieFragment();
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_douban_movie;
    }

    @Override
    public int initVariableId() {
        return BR.doubanMovieViewModel;
    }

    @Override
    public DoubanMovieViewModel initViewModel() {
        return ViewModelFactory.getInstance(getApplication(),mLifecycleSubject,RemoteDoubanMovieDataSource.getInstance())
                .createViewModel(getActivity(),DoubanMovieViewModel.class);
    }

    @Override
    public void initViewObservable() {
        mToolbarViewModel.setTitle(R.string.title_douban);
        mToolbarViewModel.setBackground(R.color.colorPrimary);
    }

    @Override
    public void initData() {
        mViewModel.loadData();
    }

    @Override
    protected void commonLoad() {

    }
}
