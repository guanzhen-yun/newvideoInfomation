package com.z.player.player;

import com.z.player.state.PlayerState;

public interface IPlayerListener {

    void playerStateChanged(PlayerState state);
}
