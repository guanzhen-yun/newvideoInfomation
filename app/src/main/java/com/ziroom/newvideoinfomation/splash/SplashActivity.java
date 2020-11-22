package com.ziroom.newvideoinfomation.splash;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.Settings;
import android.widget.TextView;

import androidx.core.app.NotificationManagerCompat;

import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseActivity;
import com.ziroom.newvideoinfomation.base.Test;
import com.ziroom.newvideoinfomation.base.ViewInject;
import com.ziroom.newvideoinfomation.main.MainActivity;

import java.io.File;

import butterknife.BindView;

import static android.provider.Settings.EXTRA_APP_PACKAGE;
import static android.provider.Settings.EXTRA_CHANNEL_ID;

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
    //    @BindView(R.id.splash_vs)
//    ViewStub mViewStub;
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

    private void checkNotifySetting() {
        if (Utils.isOpenNotification(this)) {
            goToMain();
        } else {
            Utils.showOpenNotifyDialog(this);
        }
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
            checkNotifySetting();
//            MemoryTest.getInstance(this);
//            Test.save(this);
        });

        mVideoView.setOnCompletionListener(MediaPlayer::start);
    }

    private void goToMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void setTvTimer(String timer, boolean enable) {
//        if (TextUtils.equals(timer, "跳过")) {
//            View inflate = mViewStub.inflate();
//            inflate.setVisibility(View.VISIBLE);
//            inflate.findViewById(R.id.tv_splash_timer).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                    finish();
//                }
//            });
//        }
        mTvTimer.setText(timer);
        mTvTimer.setEnabled(enable);
    }
}
