package com.sky.gank.main.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sky.gank.R;

import java.util.List;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2017/7/7.
 **/

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private String[] mTitle;

    public MainPagerAdapter(FragmentManager fm,
                            Context context,
                            List<Fragment> fragmentList){
        super(fm);
        this.mFragmentList = fragmentList;
        mTitle = new String[]{context.getString(R.string.title_meizi)
                ,context.getString(R.string.title_douban)
                ,context.getString(R.string.title_info)};
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
