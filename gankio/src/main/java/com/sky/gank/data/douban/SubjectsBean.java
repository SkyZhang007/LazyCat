package com.sky.gank.data.douban;

import com.google.gson.Gson;
import com.sky.gank.converter.ListStringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/2/28 0028.
 **/
@Entity
public class SubjectsBean {
    @Id
    private String id;
    @Convert(converter = RatingBeanConverter.class, columnType = String.class)
    private RatingBean rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    @Convert(converter = ImagesBeanConverter.class, columnType = String.class)
    private ImagesBean images;
    private String alt;
    @Convert(converter = ListStringConverter.class, columnType = String.class)
    private List<String> genres;
    @Convert(converter = CastsBeanConverter.class, columnType = String.class)
    private List<CastsBean> casts;
    @Convert(converter = DirectorsBeanConverter.class, columnType = String.class)
    private List<DirectorsBean> directors;

    @Generated(hash = 1152278129)
    public SubjectsBean(String id, RatingBean rating, String title, int collect_count, String original_title,
            String subtype, String year, ImagesBean images, String alt, List<String> genres,
            List<CastsBean> casts, List<DirectorsBean> directors) {
        this.id = id;
        this.rating = rating;
        this.title = title;
        this.collect_count = collect_count;
        this.original_title = original_title;
        this.subtype = subtype;
        this.year = year;
        this.images = images;
        this.alt = alt;
        this.genres = genres;
        this.casts = casts;
        this.directors = directors;
    }

    @Generated(hash = 155584220)
    public SubjectsBean() {
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
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

    public static class RatingBean {
        /**
         * max : 10
         * average : 6.8
         * stars : 35
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

    public static class RatingBeanConverter implements PropertyConverter<RatingBean, String> {

        @Override
        public RatingBean convertToEntityProperty(String databaseValue) {
            if(null == databaseValue){
                return null;
            }
            return new Gson().fromJson(databaseValue,RatingBean.class);
        }

        @Override
        public String convertToDatabaseValue(RatingBean entityProperty) {
            if(null == entityProperty){
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public static class ImagesBeanConverter implements PropertyConverter<ImagesBean, String> {

        @Override
        public ImagesBean convertToEntityProperty(String databaseValue) {
            if(null == databaseValue){
                return null;
            }
            return new Gson().fromJson(databaseValue,ImagesBean.class);
        }

        @Override
        public String convertToDatabaseValue(ImagesBean entityProperty) {
            if(null == entityProperty){
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2516079193.webp
         * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2516079193.webp
         * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2516079193.webp
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

    public static class CastsBeanConverter implements PropertyConverter<List<CastsBean>, String> {

        @Override
        public List<CastsBean> convertToEntityProperty(String databaseValue) {
            if(null == databaseValue){
                return null;
            }
            List<String> jsonObject = Arrays.asList(databaseValue.split(","));
            Gson gson = new Gson();
            List<CastsBean> castsBeanList = new ArrayList<>();
            for (String value:jsonObject){
                castsBeanList.add(gson.fromJson(value,CastsBean.class));
            }
            return castsBeanList;
        }

        @Override
        public String convertToDatabaseValue(List<CastsBean> entityProperty) {
            if(entityProperty==null) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            Gson gson = new Gson();
            for (CastsBean castsBean:entityProperty){
                stringBuilder.append(gson.toJson(castsBean));
                stringBuilder.append(",");
            }
            return stringBuilder.toString();
        }
    }

    public static class CastsBean {

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
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.webp
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.webp
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.webp
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

    public static class DirectorsBeanConverter implements PropertyConverter<List<DirectorsBean>, String> {

        @Override
        public List<DirectorsBean> convertToEntityProperty(String databaseValue) {
            if(null == databaseValue){
                return null;
            }
            List<String> jsonObject = Arrays.asList(databaseValue.split(","));
            Gson gson = new Gson();
            List<DirectorsBean> castsBeanList = new ArrayList<>();
            for (String value:jsonObject){
                castsBeanList.add(gson.fromJson(value,DirectorsBean.class));
            }
            return castsBeanList;
        }

        @Override
        public String convertToDatabaseValue(List<DirectorsBean> entityProperty) {
            if(entityProperty==null) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            Gson gson = new Gson();
            for (DirectorsBean directorsBean:entityProperty){
                stringBuilder.append(gson.toJson(directorsBean));
                stringBuilder.append(",");
            }
            return stringBuilder.toString();
        }
    }

    public static class DirectorsBean {

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
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1432840050.06.webp
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1432840050.06.webp
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1432840050.06.webp
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
