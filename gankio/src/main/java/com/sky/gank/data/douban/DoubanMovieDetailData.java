package com.sky.gank.data.douban;

import com.sky.gank.base.BaseResponse;

import java.util.List;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/30 0030.
 **/
public class DoubanMovieDetailData extends BaseResponse {

    /**
     * rating : {"max":10,"average":4.7,"stars":"25","min":0}
     * reviews_count : 22
     * wish_count : 4604
     * douban_site :
     * year : 2019
     * images : {"small":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2546069661.webp","large":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2546069661.webp","medium":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2546069661.webp"}
     * alt : https://movie.douban.com/subject/30168032/
     * id : 30168032
     * mobile_url : https://movie.douban.com/subject/30168032/mobile
     * title : 战斗民族养成记
     * do_count : null
     * share_url : http://m.douban.com/movie/subject/30168032
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/30168032/cinema/
     * episodes_count : null
     * countries : ["中国大陆","俄罗斯"]
     * genres : ["喜剧","爱情"]
     * collect_count : 1615
     * casts : [{"alt":"https://movie.douban.com/celebrity/1405354/","avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547713270.44.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547713270.44.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547713270.44.webp"},"name":"董畅","id":"1405354"},{"alt":"https://movie.douban.com/celebrity/1360321/","avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543230035.4.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543230035.4.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543230035.4.webp"},"name":"伊丽莎维塔·科诺诺娃","id":"1360321"},{"alt":"https://movie.douban.com/celebrity/1385343/","avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1512462248.07.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1512462248.07.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1512462248.07.webp"},"name":"维塔利·哈耶夫","id":"1385343"},{"alt":"https://movie.douban.com/celebrity/1405355/","avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227625.19.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227625.19.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227625.19.webp"},"name":"格兰特·吐火添","id":"1405355"}]
     * current_season : null
     * original_title : 战斗民族养成记
     * summary : 当“上海女婿”遇上“俄罗斯岳父”，到底会上演一段怎样的乌龙奇遇？
     由高分神剧《战斗民族养成记》原班人马共同打造的同名喜剧电影，讲述了一段上海小伙彭鹏为了追求真爱远赴俄罗斯，攻略彪悍岳父大人的异国奇遇。他将如何面对伏特加酒局、冬泳、射杀狗熊等一系列爆笑考验，令人期待…
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1405352/","avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227030.19.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227030.19.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227030.19.webp"},"name":"阿卡季·萨赫拉什维利","id":"1405352"},{"alt":"https://movie.douban.com/celebrity/1405353/","avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543297599.08.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543297599.08.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543297599.08.webp"},"name":"夏昊","id":"1405353"}]
     * comments_count : 515
     * ratings_count : 1235
     * aka : ["How I Became Russian","Как я стал русским"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private String ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(String ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 4.7
         * stars : 25
         * min : 0
         */

        private int max;
        private float average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public float getAverage() {
            return average;
        }

        public void setAverage(float average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2546069661.webp
         * large : http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2546069661.webp
         * medium : http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2546069661.webp
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1405354/
         * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547713270.44.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547713270.44.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547713270.44.webp"}
         * name : 董畅
         * id : 1405354
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547713270.44.webp
             * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547713270.44.webp
             * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547713270.44.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1405352/
         * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227030.19.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227030.19.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227030.19.webp"}
         * name : 阿卡季·萨赫拉什维利
         * id : 1405352
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227030.19.webp
             * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227030.19.webp
             * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543227030.19.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
