package com.ziroom.newvideoinfomation.main;

import androidx.fragment.app.Fragment;

import com.ziroom.mvp.mvp.ILifeCircle;
import com.ziroom.mvp.mvp.IMvpView;
import com.ziroom.mvp.mvp.MvpControler;

/**
 * Author:关震
 * Date:2020/4/5 18:47
 * Description:IMainActivityContract
 **/
public interface IMainActivityContract {
    interface IView extends IMvpView {

        void showFragment(Fragment mFragment);

        void addFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);
    }

    interface IPresenter extends ILifeCircle {
        void initHomeFragment();

        int getCurrentCheckedId();

        void replaceFragment(int mCurrentFragmentIndex);

        int getCurrentCheckedIndex();

        int getTopPosition();

        int getBottomPosition();
    }

    IView emptyView = new IView() {
        @Override
        public void showFragment(Fragment mFragment) {

        }

        @Override
        public void addFragment(Fragment mFragment) {

        }

        @Override
        public void hideFragment(Fragment mFragment) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
