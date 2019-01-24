package com.sky.gank.net.rxutil;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/8 0008.
 **/
public class HttpResult<T> {

    private boolean error;
    private T data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
