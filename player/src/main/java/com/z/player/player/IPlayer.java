package com.z.player.player;

import android.content.Context;

import com.z.player.source.IPlayerSource;

public interface IPlayer {
    /**
     * 播放器释放
     */
    void release();

    void prepare(Context context, IPlayerSource playerSource);

    void setPlayingListener(IPlayerListener listener);

    void pause();

    void reStart();
}
