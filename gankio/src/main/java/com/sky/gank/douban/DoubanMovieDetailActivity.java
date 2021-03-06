package com.sky.gank.douban;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.sky.gank.BR;
import com.sky.gank.R;
import com.sky.gank.base.BaseAppCompatActivity;
import com.sky.gank.base.ViewModelFactory;
import com.sky.gank.data.douban.RemoteDoubanMovieDataSource;
import com.sky.gank.databinding.ActivityDoubanMovieDetailBinding;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/29 0029.
 **/
public class DoubanMovieDetailActivity extends BaseAppCompatActivity<ActivityDoubanMovieDetailBinding,DoubanMovieDetailViewModel> {

    private static final String INTENT_MOVIE_ID = "movieId";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_douban_movie_detail;
    }

    @Override
    protected int initVariableId() {
        return BR.movieDetailViewModel;
    }

    @Override
    protected DoubanMovieDetailViewModel initViewModel() {
        return ViewModelFactory.getInstance(getApplication(),mLifecycleSubject,RemoteDoubanMovieDataSource.getInstance())
                .createViewModel(this,DoubanMovieDetailViewModel.class);
    }

    @Override
    protected void getIntentData(Intent intent) {
        String id = intent.getStringExtra(INTENT_MOVIE_ID);
        if(!TextUtils.isEmpty(id)){
            mViewModel.loadData(id);
        }
    }

    @Override
    protected void setToolbar() {
        mToolbarViewModel.setRightIconView(R.drawable.ic_share_white_24dp);
    }

    public static void goMovieDetail(Context context, String movieId, View view){
        Intent intent = new Intent(context,DoubanMovieDetailActivity.class);
        intent.putExtra(INTENT_MOVIE_ID,movieId);
        context.startActivity(intent);
//        if(VersionUtil.hasLollipop()){
//            ActivityOptions options =
//                    ActivityOptions.makeSceneTransitionAnimation((Activity) context, view, context.getResources().getString(R.string.douban_transition_detail));
//            context.startActivity(intent, options.toBundle());
//        } else {
//            context.startActivity(intent);
//        }
    }

    @Override
    protected boolean showBack() {
        return true;
    }
}
