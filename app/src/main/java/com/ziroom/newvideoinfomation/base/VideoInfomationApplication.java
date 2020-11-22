package com.ziroom.newvideoinfomation.base;

import android.app.Application;

import com.ziroom.newvideoinfomation.base.helper.ContextHelper;

public class VideoInfomationApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        CrashProtectManager.getInstance(this).init();
        //全局Context获取类
        ContextHelper.getInstance().init(this);
    }
}
