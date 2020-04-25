package com.ziroom.newvideoinfomation.splash;

import com.z.annotation.MvpEmptyViewFactory;
import com.ziroom.mvp.mvp.ILifeCircle;
import com.ziroom.mvp.mvp.IMvpView;

/**
 * Author:关震
 * Date:2020/4/5 14:11
 * Description:ISplashActivityConstract
 **/
public interface ISplashActivityConstract {
    @MvpEmptyViewFactory
    interface IView extends IMvpView {
        void setTvTimer(String timer, boolean isEnable);
    }

    interface IPresenter extends ILifeCircle {
        void initTimer();
    }

//    IView emptyView = new IView() {
//        @Override
//        public void setTvTimer(String timer, boolean isEnable) {
//
//        }
//
//        @Override
//        public MvpControler getMvpControler() {
//            return null;
//        }
//    };
}
