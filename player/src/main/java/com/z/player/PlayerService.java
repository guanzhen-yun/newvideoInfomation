package com.z.player;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.z.player.player.IPlayer;
import com.z.player.player.IPlayerListener;
import com.z.player.player.PlayerFactory;
import com.z.player.source.IPlayerSource;
import com.z.player.state.PlayerState;

import static com.z.player.state.PlayerState.IDLE;

public class PlayerService extends Service implements IPlayerListener {

    private PlayerState mState = IDLE;
    private IPlayer mPlayer;
    private PlayerFactory mPlayerFactory;

    @Override
    public void playerStateChanged(PlayerState state) {
        this.mState = state;
    }

    public class PlayerBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayerBinder();
    }

    //onStartCommand 在 startService有作用, start多次就会调用多次，一般用来给Service传递数据
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    //onCreate不管 start 还是bind 也不管调用几次, 只会启动一次， 常用来做全局初始化操作
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void playOrPause(IPlayerSource playerSource, Context mContext) {
        switch (mState) {
            case IDLE:
                //初始化播放器 去播放
                if(mPlayer != null) {
                    mPlayer.release();
                }
                if(mPlayerFactory == null) {
                    mPlayerFactory = new PlayerFactory();
                }
                if(mPlayer == null) {
                    mPlayer = mPlayerFactory.createPlayer(mContext);
                }
                if(mPlayer == null) {
                    //播放器创建失败
                    return;
                }
                //拿到播放器 去播放
                mPlayer.setPlayingListener(this);
                mPlayer.prepare(mContext, playerSource);
                break;
            case STARTED:
                //去暂停
                if(mPlayer != null) {
                    mPlayer.pause();
                }
                break;
            case PAUSED:
                //继续播放
                if(mPlayer != null) {
                    mPlayer.reStart();
                }
                break;
            case PREPARING:
                break;
            default:
                break;
        }
    }
}
