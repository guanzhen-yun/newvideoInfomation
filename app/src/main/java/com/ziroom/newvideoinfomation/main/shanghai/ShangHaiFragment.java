package com.ziroom.newvideoinfomation.main.shanghai;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.tools.AnimationUtil;
import com.ziroom.newvideoinfomation.base.BaseFragment;
import com.ziroom.newvideoinfomation.base.ViewInject;
import com.ziroom.newvideoinfomation.base.tools.DoubleClickListener;
import com.ziroom.newvideoinfomation.main.shanghai.adapter.ShanghaiAdapter2;
import com.ziroom.newvideoinfomation.main.shanghai.lf.IPlayerServiceContract;
import com.ziroom.newvideoinfomation.main.shanghai.presenter.PlayerServicePresenter;

import butterknife.BindView;

/**
 * Author:关震
 * Date:2020/4/5 19:01
 * Description:ShangHaiFragment
 **/
@ViewInject(mainlayoutid = R.layout.fragment_shanghai)
public class ShangHaiFragment extends BaseFragment implements IPlayerServiceContract.IView {

    IPlayerServiceContract.IPresenter mPresenter = new PlayerServicePresenter(this);

    @BindView(R.id.tv_shanghai_welcome)
    TextView mTvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingtoolbarlayout)
    CollapsingToolbarLayout mShanghaiCollapsingtoolbarlayout;
    @BindView(R.id.shanghai_app_barlayout)
    AppBarLayout mShanghaiAppBarlayout;
    @BindView(R.id.shanghai_recyclerview)
    RecyclerView mShanghaiRecyclerview;
    @BindView(R.id.tv_marquee_title)
    TextView mTvTitle;
    private boolean mIsPlaying;

    @Override
    public void afterBindView() {
        initRecyclerView();
        initListener();
    }

    private void initRecyclerView() {
        mShanghaiRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
//        mShanghaiRecyclerview.setAdapter(new ShanghaiAdapter(getActivity(), ShanghaiDataManager.getData(), false));
        mShanghaiRecyclerview.setAdapter(new ShanghaiAdapter2());
    }

    private void initListener() {
        mShanghaiAppBarlayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (-verticalOffset < appBarLayout.getMeasuredHeight() / 2) {
                mTvShanghaiWelcome.setVisibility(View.INVISIBLE);
                mTvTitle.setVisibility(View.INVISIBLE);
            } else {
                mTvShanghaiWelcome.setVisibility(View.VISIBLE);
                if (mIsPlaying) {
                    mTvTitle.setVisibility(View.VISIBLE);
                }
            }
        });

        mTvShanghaiWelcome.setOnClickListener(new DoubleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvTitle.clearAnimation();
                mTvShanghaiWelcome.clearAnimation();
                if (mIsPlaying) {
                    //关闭音视频动画
                    mTvTitle.setVisibility(View.GONE);
                    AnimationUtil.startTranslationXAnim(mTvShanghaiWelcome, mTvShanghaiWelcome.getTranslationX(), mTvShanghaiWelcome.getTranslationX() + 150, null);
                    AnimationUtil.startTranslationXAnim(mTvTitle, mTvTitle.getTranslationX(), mTvTitle.getTranslationX() + 150, null);
                    mPresenter.playOrPaused();
                } else {
                    //播放音视频动画
                    AnimationUtil.startTranslationXAnim(mTvShanghaiWelcome, mTvShanghaiWelcome.getTranslationX(), mTvShanghaiWelcome.getTranslationX() - 150, null);
                    AnimationUtil.startTranslationXAnim(mTvTitle, mTvTitle.getTranslationX(), mTvTitle.getTranslationX() - 150, new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mTvTitle.setVisibility(View.VISIBLE);
                            //启动Service 去播放后台音乐
                            mPresenter.bindService(mContext);
                        }
                    });
                }
                mIsPlaying = !mIsPlaying;
            }
        }));
    }
}
