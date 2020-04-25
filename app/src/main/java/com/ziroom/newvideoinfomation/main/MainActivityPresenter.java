package com.ziroom.newvideoinfomation.main;

import androidx.fragment.app.Fragment;

import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.main.beijing.BeiJingFragment;
import com.ziroom.newvideoinfomation.main.hangzhou.HangZhouFragment;
import com.ziroom.newvideoinfomation.main.shanghai.ShangHaiFragment;
import com.ziroom.newvideoinfomation.main.shenzhen.ShenZhenFragment;
import com.ziroom.newvideoinfomation.main.tools.MainConstantTool;
import com.ziroom.mvp.mvp.base.BaseMvpPresenter;

/**
 * Author:关震
 * Date:2020/4/5 18:46
 * Description:MainActivityPresenter
 **/
public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.IView> implements IMainActivityContract.IPresenter {

    //当前Fragment的角标
    private int mCurrentFragmentIndex;
    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentCheckedId;
    private int mTopPosition;
    private int mBottomPosition;

    public MainActivityPresenter(IMainActivityContract.IView view) {
        super(view);
    }

    @Override
    protected IMainActivityContract.IView getEmptyView() {
        return IMainActivityContract.emptyView;
    }

    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = MainConstantTool.SHANGHAI;
        replaceFragment(mCurrentFragmentIndex);
    }

    @Override
    public int getCurrentCheckedId() {
        return mCurrentCheckedId;
    }

    @Override
    public int getCurrentCheckedIndex() {
        return mCurrentFragmentIndex;
    }

    //切换Fragment的方法
    @Override
    public void replaceFragment(int mCurrentFragmentIndex) {
        //断点---condition i== 2 可以控制断点执行次数
        for (int i = 0; i < mFragments.length; i++) {
            if (mCurrentFragmentIndex != i) {
                if (mFragments[i] != null) {
                    hideFragment(mFragments[i]);
                }
            }
        }

        Fragment mFragment = mFragments[mCurrentFragmentIndex];
        if (mFragment != null) {
            addAndShowFragment(mFragment);
            setCurChecked(mCurrentFragmentIndex);
        } else {
            newCurrentFragment(mCurrentFragmentIndex);
            setCurChecked(mCurrentFragmentIndex);
        }
    }

    //记录当前 角标
    private void setCurChecked(int mCurrentFragmentIndex) {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex) {
            case MainConstantTool.SHANGHAI:
                mCurrentCheckedId = R.id.rb_main_shanghai;
                mTopPosition = MainConstantTool.SHANGHAI;
                break;
            case MainConstantTool.HANGZHOU:
                mCurrentCheckedId = R.id.rb_main_hangzhou;
                mTopPosition = MainConstantTool.HANGZHOU;
                break;
            case MainConstantTool.BEIJING:
                mCurrentCheckedId = R.id.rb_main_nav_home_beijing;
                mBottomPosition = MainConstantTool.BEIJING;
                break;
            case MainConstantTool.SHENZHEN:
                mCurrentCheckedId = R.id.rb_main_nav_car_source_shenzhen;
                mBottomPosition = MainConstantTool.SHENZHEN;
                break;
        }
    }

    //创建 当前Fragment
    private void newCurrentFragment(int mCurrentFragmentIndex) {
        Fragment fragment = null;
        switch (mCurrentFragmentIndex) {
            case MainConstantTool.SHANGHAI:
                fragment = new ShangHaiFragment();
                break;
            case MainConstantTool.HANGZHOU:
                fragment = new HangZhouFragment();
                break;
            case MainConstantTool.BEIJING:
                fragment = new BeiJingFragment();
                break;
            case MainConstantTool.SHENZHEN:
                fragment = new ShenZhenFragment();
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }


    //显示Fragment
    private void addAndShowFragment(Fragment mFragment) {
        if (mFragment.isAdded()) {
            getView().showFragment(mFragment);
        } else {
            getView().addFragment(mFragment);
        }
    }

    private void hideFragment(Fragment mFragment) {
        if (mFragment != null && mFragment.isVisible()) {
            getView().hideFragment(mFragment);
        }
    }

    @Override
    public int getTopPosition() {
        return mTopPosition;
    }

    @Override
    public int getBottomPosition() {
        return mBottomPosition;
    }
}
