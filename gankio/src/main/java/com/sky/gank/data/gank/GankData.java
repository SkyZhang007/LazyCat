package com.sky.gank.data.gank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sky.gank.base.BaseResponse;

import java.util.Date;
import java.util.List;

/**
 * 类名称：GankData
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/22 0022
 **/
public class GankData extends BaseResponse {

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {

        @SerializedName("Android")
        private List<Gank> androidList;
        @SerializedName("休息视频")
        private List<Gank> videoList;
        @SerializedName("iOS")
        private List<Gank> iOSList;
        @SerializedName("福利")
        private List<Gank> meiziList;
        @SerializedName("拓展资源")
        private List<Gank> resourcesList;
        @SerializedName("瞎推荐")
        private List<Gank> recommendList;
        @SerializedName("App")
        private List<Gank> appList;

        public List<Gank> getAndroidList() {
            return androidList;
        }

        public void setAndroidList(List<Gank> androidList) {
            this.androidList = androidList;
        }

        public List<Gank> getVideoList() {
            return videoList;
        }

        public void setVideoList(List<Gank> videoList) {
            this.videoList = videoList;
        }

        public List<Gank> getiOSList() {
            return iOSList;
        }

        public void setiOSList(List<Gank> iOSList) {
            this.iOSList = iOSList;
        }

        public List<Gank> getMeiziList() {
            return meiziList;
        }

        public void setMeiziList(List<Gank> meiziList) {
            this.meiziList = meiziList;
        }

        public List<Gank> getResourcesList() {
            return resourcesList;
        }

        public void setResourcesList(List<Gank> resourcesList) {
            this.resourcesList = resourcesList;
        }

        public List<Gank> getRecommendList() {
            return recommendList;
        }

        public void setRecommendList(List<Gank> recommendList) {
            this.recommendList = recommendList;
        }

        public List<Gank> getAppList() {
            return appList;
        }

        public void setAppList(List<Gank> appList) {
            this.appList = appList;
        }
    }

    public static class Gank{
        private String titleType;
        private String url;
        private String type;
        private String desc;
        private String who;
        private boolean used;
        private Date createdAt;
        private Date updatedAt;
        private Date publishedAt;

        public Gank(String titleType) {
            this.titleType = titleType;
        }

        public String getTitleType() {
            return titleType;
        }

        public void setTitleType(String titleType) {
            this.titleType = titleType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Date getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(Date publishedAt) {
            this.publishedAt = publishedAt;
        }
    }
}
