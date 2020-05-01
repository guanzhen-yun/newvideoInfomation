package com.ziroom.newvideoinfomation.main.shanghai.presenter;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.z.player.PlayerService;
import com.z.player.source.RawPlayerSource;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BasePresenter;
import com.ziroom.newvideoinfomation.base.helper.ContextHelper;
import com.ziroom.newvideoinfomation.main.shanghai.lf.IPlayerServiceContract;

public class PlayerServicePresenter extends BasePresenter<IPlayerServiceContract.IView> implements IPlayerServiceContract.IPresenter {
    private PlayerService playerService;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            //IOC 数据回调 和Service连接成功后调用
            PlayerService.PlayerBinder binder = (PlayerService.PlayerBinder) service;
            playerService = binder.getService();
            playOrPaused();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //和Service连接断开后调用
            if (playerService != null) {
                playerService.unbindService(mConnection);
                playerService = null;
            }
        }
    };

    public PlayerServicePresenter(IPlayerServiceContract.IView view) {
        super(view);
    }

    @Override
    public void bindService(Context context) {
        if (playerService != null) {
            playOrPaused();
        } else {
            Intent intent = new Intent(context, PlayerService.class);
            context.bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        }
    }

    @Override
    public void playOrPaused() {
        if (playerService != null) {
            //开启播放音乐
            playerService.playOrPause(new RawPlayerSource().setPath(ContextHelper.getInstance().getApplicationContext().getPackageName(), R.raw.minyao), ContextHelper.getInstance().getApplicationContext());
        }
    }
}
