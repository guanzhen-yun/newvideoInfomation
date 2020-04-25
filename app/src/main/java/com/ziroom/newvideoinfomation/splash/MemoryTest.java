package com.ziroom.newvideoinfomation.splash;

import android.content.Context;

public class MemoryTest {
    private static MemoryTest mInstance;
    private final Context context;

    public MemoryTest(Context context) {//建议用getApplicationContext
        this.context = context.getApplicationContext();
    }

    public static MemoryTest getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new MemoryTest(context);
        }
        return mInstance;
    }
}
