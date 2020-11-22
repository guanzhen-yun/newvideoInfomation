package com.ziroom.newvideoinfomation.splash;

import com.ziroom.newvideoinfomation.base.BasePresenter;

/**
 * Author:关震
 * Date:2020/4/5 12:17
 * Description:SplashTimerPresenter
 **/
public class SplashTimerPresenter extends BasePresenter<ISplashActivityConstract.IView> implements ISplashActivityConstract.IPresenter {
    private CustomCountDownTimer timer;

    public SplashTimerPresenter(ISplashActivityConstract.IView view) {
        super(view);
    }

    @Override
    public void initTimer() {
        timer = new CustomCountDownTimer(5/5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                getView().setTvTimer(time + "秒", false);
            }

            @Override
            public void onFinish() {
                getView().setTvTimer("跳过", true);
            }
        });
        timer.start();
    }

    public void cancel() {
        timer.cancel();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();
    }

    /**
     * 防止 空指针异常
     */
//    @Override
//    protected ISplashActivityConstract.IView getEmptyView() {
//        return ISplashActivityConstract.emptyView;
//    }
}
