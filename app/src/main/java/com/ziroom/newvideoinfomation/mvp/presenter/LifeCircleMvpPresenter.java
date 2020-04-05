package com.ziroom.newvideoinfomation.mvp.presenter;

import com.ziroom.newvideoinfomation.mvp.ILifeCircle;
import com.ziroom.newvideoinfomation.mvp.IMvpView;
import com.ziroom.newvideoinfomation.mvp.MvpControler;

import java.lang.ref.WeakReference;

/**
 * Author:关震
 * Date:2020/4/5 12:34
 * Description:LifeCircleMvpPresenter
 **/
public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {

    protected WeakReference<T> weakReference;

    protected LifeCircleMvpPresenter() {
        super();
    }

    public LifeCircleMvpPresenter(IMvpView iMvpView) {
        super();
        attachView(iMvpView);
        MvpControler mvpControler = iMvpView.getMvpControler();
        mvpControler.savePresenter(this);
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        if (weakReference == null) {
            weakReference = new WeakReference(iMvpView);
        } else {
            T view = weakReference.get();
            if (view != iMvpView) {
                weakReference = new WeakReference(iMvpView);
            }
        }
    }

    @Override
    public void onDestroy() {
        weakReference = null;
    }

    protected T getView() {
        T view = weakReference != null ? weakReference.get() : null;
        if (view == null) {
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();
}
