package com.ziroom.newvideoinfomation.splash;

import com.ziroom.newvideoinfomation.mvp.ILifeCircle;
import com.ziroom.newvideoinfomation.mvp.IMvpView;
import com.ziroom.newvideoinfomation.mvp.MvpControler;

/**
 * Author:关震
 * Date:2020/4/5 14:11
 * Description:ISplashActivityConstract
 **/
public interface ISplashActivityConstract {
    interface IView extends IMvpView {
        void setTvTimer(String timer, boolean isEnable);
    }

    interface IPresenter extends ILifeCircle {
        void initTimer();
    }

    IView emptyView = new IView() {
        @Override
        public void setTvTimer(String timer, boolean isEnable) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
