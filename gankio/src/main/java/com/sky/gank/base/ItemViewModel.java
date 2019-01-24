package com.sky.gank.base;

import android.support.annotation.NonNull;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/2 0002.
 **/
public class ItemViewModel <VM extends BaseViewModel>{
    protected VM viewModel;

    public ItemViewModel(@NonNull VM viewModel) {
        this.viewModel = viewModel;
    }
}
