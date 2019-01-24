package com.sky.gank.util;

import android.os.Build;

/**
 * 类名称：VersionUtil
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/18 0018
 **/
public class VersionUtil {

    /**
     * Uses static final constants to detect if the device's platform version is Lollipop or
     * later.
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * Uses static final constants to detect if the device's platform version is Lollipop or
     * later.
     */
    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

}
