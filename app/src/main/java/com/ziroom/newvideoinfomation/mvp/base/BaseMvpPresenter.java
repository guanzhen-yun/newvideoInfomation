package com.ziroom.newvideoinfomation.mvp.base;

import android.content.Intent;
import android.os.Bundle;

import com.ziroom.newvideoinfomation.mvp.IMvpView;
import com.ziroom.newvideoinfomation.mvp.presenter.LifeCircleMvpPresenter;

/**
 * Author:关震
 * Date:2020/4/5 12:46
 * Description:BaseMvpPresenter p层的中间类
 **/
public abstract class BaseMvpPresenter<T extends IMvpView> extends LifeCircleMvpPresenter<T> {

    public BaseMvpPresenter(T view) {
        super(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }
}
