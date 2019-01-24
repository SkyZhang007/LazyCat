package com.sky.gank.command;

/**
 * 类名称：
 * 类功能：
 * 类作者：Administrator
 * 类日期：2019/1/8 0008.
 **/
public interface BindingConsumer<T> {
    void call(T t);
}
