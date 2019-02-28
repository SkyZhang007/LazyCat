package com.sky.gank.converter;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/2/27 0027.
 **/
public class ListStringConverter implements PropertyConverter<List<String>, String> {

    @Override
    public List<String> convertToEntityProperty(String databaseValue) {
        if(null == databaseValue){
            return null;
        }
        return Arrays.asList(databaseValue.split(","));
    }

    @Override
    public String convertToDatabaseValue(List<String> entityProperty) {
        if(entityProperty==null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s:entityProperty){
            stringBuilder.append(s);
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }
}
