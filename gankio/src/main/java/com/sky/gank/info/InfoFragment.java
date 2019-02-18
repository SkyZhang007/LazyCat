package com.sky.gank.info;

import com.sky.gank.R;
import com.sky.gank.BR;
import com.sky.gank.base.BaseFragment;
import com.sky.gank.base.ViewModelFactory;
import com.sky.gank.databinding.FragmentInfoBinding;

public class InfoFragment extends BaseFragment<FragmentInfoBinding,InfoViewModel>{

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_info;
    }

    @Override
    public int initVariableId() {
        return BR.infoViewModel;
    }

    @Override
    public InfoViewModel initViewModel() {
        return ViewModelFactory.getInstance(getApplication(),mLifecycleSubject,null)
                .createViewModel(getActivity(),InfoViewModel.class);
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void commonLoad() {

    }
}
