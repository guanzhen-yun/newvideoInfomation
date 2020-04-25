package com.ziroom.newvideoinfomation.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Debug;
import android.widget.TextView;

import com.ziroom.newvideoinfomation.base.BaseActivity;
import com.ziroom.newvideoinfomation.main.MainActivity;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.ViewInject;

import java.io.File;

import butterknife.BindView;

/**
 * Author:关震
 * Date:2020/4/1 19:19
 * Description:SplashActivity
 * Ctrl+Alt+F  生成全局的变量快捷键  fbc 生成findViewById的代码
 **/
@ViewInject(mainlayoutid = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityConstract.IView {

    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoView;
    @BindView(R.id.tv_splash_timer)
    TextView mTvTimer;
    private ISplashActivityConstract.IPresenter timerPresenter;

    @Override
    public void afterBindView() {
//        Debug.waitForDebugger();
        initTimerPresenter();
        initListener();
        initVideo();
    }

    private void initTimerPresenter() {
        timerPresenter = new SplashTimerPresenter(this);
        timerPresenter.initTimer();
    }

    private void initVideo() {
        mTvTimer.setEnabled(false);
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(MediaPlayer::start);
    }

    private void initListener() {
        mTvTimer.setOnClickListener(view -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        });

        mVideoView.setOnCompletionListener(MediaPlayer::start);
    }

    @Override
    public void setTvTimer(String timer, boolean enable) {
        mTvTimer.setText(timer);
        mTvTimer.setEnabled(enable);
    }
}
