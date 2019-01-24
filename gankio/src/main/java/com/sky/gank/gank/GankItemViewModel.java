package com.sky.gank.gank;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sky.gank.base.ItemViewModel;
import com.sky.gank.data.gank.GankData;

/**
 * 类名称：GankItemViewModel
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/22 0022
 **/
public class GankItemViewModel extends ItemViewModel<GankViewModel> {

    public GankData.Gank mGank;
    public final ObservableField<Boolean> mFirstItem = new ObservableField<>();

    public GankItemViewModel(@NonNull GankViewModel viewModel, GankData.Gank gank) {
        super(viewModel);
        this.mGank = gank;
        if(!TextUtils.isEmpty(gank.getTitleType())){
            if(gank.getTitleType().equals("Android")){
                mFirstItem.set(true);
            } else {
                mFirstItem.set(false);
            }
        }
    }

}
