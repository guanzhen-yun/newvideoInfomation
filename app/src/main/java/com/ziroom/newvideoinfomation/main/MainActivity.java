package com.ziroom.newvideoinfomation.main;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseActivity;
import com.ziroom.newvideoinfomation.base.ViewInject;
import com.ziroom.newvideoinfomation.main.tools.MainConstantTool;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainlayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.IView {

    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);

    @BindView(R.id.fac_main_home)
    FloatingActionButton mFacMainHome;
    @BindView(R.id.rb_main_shanghai)
    LottieAnimationView mRbMainShanghai;
//    RadioButton mRbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    LottieAnimationView mRbMainHangzhou;
//    RadioButton mRbMainHangzhou;
    @BindView(R.id.rg_main_top)
    LinearLayout mRgMainTop;
//    RadioGroup mRgMainTop;
    @BindView(R.id.fl_main_bottom)
    FrameLayout mFlMainBottom;
    @BindView(R.id.rb_main_nav_home_beijing)
    RadioButton mRbMainNavHomeBeijing;
    @BindView(R.id.rb_main_nav_car_source_shenzhen)
    RadioButton mRbMainNavCarSourceShenzhen;
    @BindView(R.id.rg_main_bottom)
    RadioGroup mRgMainBottom;

    private boolean isChangeTopOrBottom;

    @Override
    public void afterBindView() {
        initHomeFragment();
        changeAnima(mRgMainBottom, mRgMainTop);
        initCheckListener();
    }

    private void initCheckListener() {
        mRbMainShanghai.playAnimation();
        mRbMainShanghai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( mRbMainShanghai.getId() == mPresenter.getCurrentCheckedId()) {
                    return;
                }
                mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
                mRbMainShanghai.playAnimation();
                mRbMainHangzhou.reverseAnimation();
            }
        });

        mRbMainHangzhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRbMainHangzhou.getId() == mPresenter.getCurrentCheckedId()) {
                    return;
                }
                mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
                mRbMainHangzhou.playAnimation();
                mRbMainShanghai.reverseAnimation();
            }
        });

//        mRbMainShanghai.setChecked(true);
//        mRgMainTop.setOnCheckedChangeListener((group, checkedId) -> {
//            if (checkedId == mPresenter.getCurrentCheckedId()) {
//                return;
//            }
//            switch (checkedId) {
//                case R.id.rb_main_shanghai:
//                    mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
//                    break;
//                case R.id.rb_main_hangzhou:
//                    mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
//                    break;
//            }
//        });

        mRgMainBottom.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == mPresenter.getCurrentCheckedId()) {
                return;
            }
            switch (checkedId) {
                case R.id.rb_main_nav_home_beijing:
                    mPresenter.replaceFragment(MainConstantTool.BEIJING);
                    break;
                case R.id.rb_main_nav_car_source_shenzhen:
                    mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
                    break;
            }
        });
    }

    /**
     * 初始化Fragment
     */

    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }

    @OnClick(R.id.fac_main_home)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if(isChangeTopOrBottom) {
                    changeAnima(mRgMainTop, mRgMainBottom);
                    handleTopPosition();
                } else {
                    changeAnima(mRgMainBottom, mRgMainTop);
                    handleBottomPosition();
                }
                break;
        }
    }

    //北京 深圳
    private void handleBottomPosition() {
        if(mPresenter.getTopPosition() != 1) {
            mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
//            mRbMainShanghai.setChecked(true);
            mRbMainShanghai.playAnimation();
        } else {
            mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
//            mRbMainHangzhou.setChecked(true);
            mRbMainHangzhou.playAnimation();
        }
    }

    //上海 杭州
    private void handleTopPosition() {
        if(mPresenter.getBottomPosition() != 3) {
            mPresenter.replaceFragment(MainConstantTool.BEIJING);
            mRbMainNavHomeBeijing.setChecked(true);
        } else {
            mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
            mRbMainNavCarSourceShenzhen.setChecked(true);
        }
    }

    private void changeAnima(ViewGroup gone, ViewGroup show) {
//    private void changeAnima(RadioGroup gone, RadioGroup show) {
        //消失的动画
        gone.clearAnimation();//清除自身动画
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        //展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);
        show.setAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }

    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content, mFragment).commit();
    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }
}
