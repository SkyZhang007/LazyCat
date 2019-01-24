package com.sky.gank.data.gank;

import java.io.Serializable;

/**
 * 类名称：GankBean
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/18 0018
 **/
public class GankBean implements Serializable {

    private static final long serialVersionUID = 4392452594388462611L;

    private String imageUrl;
    private String createData;

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
