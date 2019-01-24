package com.sky.gank.viewgroup;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/7 0007.
 **/
import android.databinding.ViewDataBinding;

public interface IBindingItemViewModel<V extends ViewDataBinding> {
    void injecDataBinding(V binding);
}