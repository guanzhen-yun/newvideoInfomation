package com.ziroom.newvideoinfomation.main.shanghai.lf;

import android.content.Context;

import com.z.annotation.MvpEmptyViewFactory;
import com.ziroom.mvp.mvp.ILifeCircle;
import com.ziroom.mvp.mvp.IMvpView;
import com.ziroom.newvideoinfomation.main.shanghai.dto.ShangHaiDetailBean;

/**
 * Author:关震
 * Date:2020/4/11 14:52
 * Description:IShanghaiDetailContract
 **/
public interface IPlayerServiceContract {
    @MvpEmptyViewFactory
    interface IView extends IMvpView {
    }

    interface IPresenter extends ILifeCircle {
        void bindService(Context context);

        void playOrPaused();
    }
}
