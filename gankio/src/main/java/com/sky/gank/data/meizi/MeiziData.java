package com.sky.gank.data.meizi;

import com.sky.gank.base.BaseResponse;

import java.util.List;

public class MeiziData extends BaseResponse {

    private boolean error;
    private List<MeizhiBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<MeizhiBean> getResults() {
        return results;
    }

    public void setResults(List<MeizhiBean> results) {
        this.results = results;
    }

    public static class MeizhiBean {
        /**
         * _id : 5971760e421aa90ca209c4af
         * createdAt : 2017-07-21T11:33:34.104Z
         * desc : 7-21
         * publishedAt : 2017-07-21T12:39:43.370Z
         * source : chrome
         * type : 福利
         * url : http://ww1.sinaimg.cn/large/610dc034ly1fhrcmgo6p0j20u00u00uu.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        public String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        public String videoUrl;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        @Override
        public String toString() {
            return "MeizhiBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    ", videoUrl='" + videoUrl + '\'' +
                    '}';
        }
    }

}
