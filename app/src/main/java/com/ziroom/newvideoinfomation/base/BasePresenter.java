package com.ziroom.newvideoinfomation.base;

import com.ziroom.mvp.mvp.IMvpView;
import com.ziroom.mvp.mvp.base.BaseMvpPresenter;
import com.ziroom.task.LfTask;
import com.ziroom.task.TaskHelper;

import ziroom.information.mvp.MvpEmptyViewFactory;

/**
 * Author:关震
 * Date:2020/4/11 15:09
 * Description:BasePresenter 集成mvp 及 网络请求 快捷方式
 **/
public abstract class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {

    public BasePresenter(T view) {
        super(view);
    }

    public void submitTask(LfTask task) {
        TaskHelper.submitTask(task, task);
    }

    @Override
    protected T getEmptyView() {
        T t = null;
        Class superClassGenricType = GenericsUtils.getSuperClassGenricType(this.getClass(), 0);
        try {
            t = (T) MvpEmptyViewFactory.create(superClassGenricType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
