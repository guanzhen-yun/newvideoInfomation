package com.ziroom.newvideoinfomation.main.hangzhou.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.z.refresh.BaseRefreshManager;
import com.ziroom.newvideoinfomation.R;

public class MeiTuanRefreshManager extends BaseRefreshManager {
    private ImageView mImageView;

    public MeiTuanRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView() {
        View view = mLayoutInflater.inflate(R.layout.meituan_header_refresh_layout, null, false);
        mImageView = view.findViewById(R.id.loading);
        return view;
    }

    @Override
    public void downRefresh() {

    }

    //释放刷新的时候 会变成美团的吉祥物
    @Override
    public void releaseRefresh() {
        mImageView.setImageResource(R.drawable.mei_tuan_loading_pre);
        AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void idelRefresh() {
        mImageView.clearAnimation();
        mImageView.setImageResource(R.mipmap.pull_image);
        mImageView.setScaleX(0);
        mImageView.setScaleY(0);
    }

    @Override
    public void refreshing() {
        mImageView.setImageResource(R.drawable.mei_tuan_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void downRefreshPercent(float percent) {
        mImageView.setScaleX(percent);
        mImageView.setScaleY(percent);
    }
}
