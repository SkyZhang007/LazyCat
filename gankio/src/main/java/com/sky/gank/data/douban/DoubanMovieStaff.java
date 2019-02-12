package com.sky.gank.data.douban;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/2/12 0012.
 **/
public class DoubanMovieStaff {

    private String name;
    private String smallImg;
    private String mediumImg;
    private String largeImg;
    // 0:导演 1:演员
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public String getMediumImg() {
        return mediumImg;
    }

    public void setMediumImg(String mediumImg) {
        this.mediumImg = mediumImg;
    }

    public String getLargeImg() {
        return largeImg;
    }

    public void setLargeImg(String largeImg) {
        this.largeImg = largeImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
