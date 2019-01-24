package com.sky.gank.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

import com.sky.gank.util.LogUtils;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/2 0002.
 **/
public class MyRecyclerViewAdapter <T> extends BindingRecyclerViewAdapter<T> {

    @Override
    public void onBindBinding(ViewDataBinding binding, int variableId, @LayoutRes int layoutRes, int position, T item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        LogUtils.d(LogUtils.TAG, "bound binding: " + binding + " at position: " + position);
    }
}
