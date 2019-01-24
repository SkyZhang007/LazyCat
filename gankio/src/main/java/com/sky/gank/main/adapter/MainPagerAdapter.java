package com.sky.gank.main.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sky.gank.R;
import com.sky.gank.douban.DoubanMovieFragment;
import com.sky.gank.meizi.MeiziFragment;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2017/7/7.
 **/

public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private MeiziFragment mMeiziFragment;
    private DoubanMovieFragment mDoubanMovieFragment;

    public MainPagerAdapter(FragmentManager fm,
                            Context context,
                            MeiziFragment meiziFragment,
                            DoubanMovieFragment doubanMovieFragment){
        super(fm);
        this.mMeiziFragment = meiziFragment;
        this.mDoubanMovieFragment = doubanMovieFragment;
        titles = new String[]{context.getString(R.string.title_meizi),context.getString(R.string.title_douban)};
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return mMeiziFragment;
        } else if(position == 1){
            return mDoubanMovieFragment;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
