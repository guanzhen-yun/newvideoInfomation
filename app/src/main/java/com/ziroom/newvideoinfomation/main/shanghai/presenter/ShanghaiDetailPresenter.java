package com.ziroom.newvideoinfomation.main.shanghai.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.ziroom.http.result.IResult;
import com.ziroom.mvp.mvp.base.BaseMvpPresenter;
import com.ziroom.newvideoinfomation.base.BasePresenter;
import com.ziroom.newvideoinfomation.base.JHTask;
import com.ziroom.newvideoinfomation.main.shanghai.dto.ShangHaiDetailBean;
import com.ziroom.newvideoinfomation.main.shanghai.module.ShangHaiDetailHttpTask;
import com.ziroom.task.LfTask;

import java.io.IOException;

import okhttp3.Response;

/**
 * Author:关震
 * Date:2020/4/11 14:50
 * Description:ShanghaiDetailPresenter
 **/
public class ShanghaiDetailPresenter extends BasePresenter<IShanghaiDetailContract.IView> implements IShanghaiDetailContract.IPresenter {
    public ShanghaiDetailPresenter(IShanghaiDetailContract.IView view) {
        super(view);
    }

    @Override
    protected IShanghaiDetailContract.IView getEmptyView() {
        return IShanghaiDetailContract.emptyView;
    }

    @Override
    public void getNetData(int pagesize) {
        //1.数据的结果解析怎么来做
        //2.相同任务的去重工作

//        submitTask(new LfTask() {
//            //一定要回调到主线程
//            @Override
//            public void onSuccess(Object o) {
//                //获取网络结果
//                Log.e("getNetData", o.toString());
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//                Log.e("getNetData", throwable.toString());
//            }
//
//            //运行于子线程
//            @Override
//            public Object onBackground() {
//                IResult desc = new ShangHaiDetailHttpTask().getXiaoHuaList("desc", "1", "2");
//                return desc;
//            }
//        });
/**
 * 架构师的必备条件
 * 1.合理利用继承关系
 * 2.合理利用抽象编程
 * 3.合理利用泛型传递数据
 * 4.合理利用一些设计模式
 */
        submitTask(new JHTask<ShangHaiDetailBean>() {
            @Override
            public void onSuccess(IResult<ShangHaiDetailBean> t) {
                ShangHaiDetailBean data = t.data();
                getView().showData(data);
//                Gson gson = new Gson();
//                String s = gson.toJson(data);
//                Log.e("ShanghaiDetailPresenter", s);
            }

            @Override
            public IResult<ShangHaiDetailBean> onBackground() {
                return new ShangHaiDetailHttpTask<ShangHaiDetailBean>().getXiaoHuaList("desc", "1", pagesize + "");
            }
        });
    }
}
