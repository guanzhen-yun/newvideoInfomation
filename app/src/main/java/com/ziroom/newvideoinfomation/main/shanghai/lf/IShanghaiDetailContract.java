package com.ziroom.newvideoinfomation.main.shanghai.lf;

import com.z.annotation.MvpEmptyViewFactory;
import com.ziroom.mvp.mvp.ILifeCircle;
import com.ziroom.mvp.mvp.IMvpView;
import com.ziroom.newvideoinfomation.main.shanghai.dto.ShangHaiDetailBean;

/**
 * Author:关震
 * Date:2020/4/11 14:52
 * Description:IShanghaiDetailContract
 **/
public interface IShanghaiDetailContract {
    @MvpEmptyViewFactory
    interface IView extends IMvpView {

        void showData(ShangHaiDetailBean data);
    }

    interface IPresenter extends ILifeCircle {
        void getNetData(int pagesize);
    }

//    IShanghaiDetailContract.IView emptyView = new IShanghaiDetailContract.IView() {
//
//        @Override
//        public void showData(ShangHaiDetailBean data) {
//
//        }
//
//        @Override
//        public MvpControler getMvpControler() {
//            return null;
//        }
//    };
}
