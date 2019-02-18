package com.sky.gank.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.sky.gank.R;
import com.sky.gank.base.BaseAppCompatActivity;
import com.sky.gank.base.BaseViewModel;
import com.sky.gank.douban.DoubanMovieFragment;
import com.sky.gank.info.InfoFragment;
import com.sky.gank.main.adapter.MainPagerAdapter;
import com.sky.gank.meizi.MeiziFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sky
 */
public class MainActivity extends BaseAppCompatActivity {

    private ViewPager viewPager;
    private MeiziFragment mMeiziFragment;
    private DoubanMovieFragment mDoubanMovieFragment;
    private InfoFragment mInfoFragment;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragments(savedInstanceState);
        initViewPager();
        mBottomNavigationView = findViewById(R.id.bnv_home);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int initVariableId() {
        return 0;
    }

    @Override
    protected BaseViewModel initViewModel() {
        return null;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // getOrder = android:orderInCategory="x"
            viewPager.setCurrentItem(item.getOrder());
            return true;
        }
    };

    private void initFragments(Bundle savedInstanceState) {
        FragmentManager fm = getSupportFragmentManager();
        if(savedInstanceState == null){
            mMeiziFragment = MeiziFragment.newInstance();
            mDoubanMovieFragment = DoubanMovieFragment.newInstance();
            mInfoFragment = InfoFragment.newInstance();
        } else {
            mMeiziFragment = (MeiziFragment) fm.getFragment(savedInstanceState,MeiziFragment.class.getSimpleName());
            mDoubanMovieFragment = (DoubanMovieFragment) fm.getFragment(savedInstanceState,DoubanMovieFragment.class.getSimpleName());
            mInfoFragment = (InfoFragment) fm.getFragment(savedInstanceState,InfoFragment.class.getSimpleName());
        }
    }

    private void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(mMeiziFragment);
        fragmentList.add(mDoubanMovieFragment);
        fragmentList.add(mInfoFragment);

        viewPager = findViewById(R.id.vp_home);
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager()
                ,MainActivity.this,fragmentList);
        viewPager.setAdapter(mainPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                mBottomNavigationView.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

}
