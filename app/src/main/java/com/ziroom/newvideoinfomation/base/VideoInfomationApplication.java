package com.ziroom.newvideoinfomation.base;

import android.app.Application;

import com.ziroom.newvideoinfomation.base.crash.CrashProtectManager;

public class VideoInfomationApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashProtectManager.getInstance(this).init();
    }
}
