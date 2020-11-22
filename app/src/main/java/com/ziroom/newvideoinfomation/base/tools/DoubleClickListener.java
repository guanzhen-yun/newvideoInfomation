package com.ziroom.newvideoinfomation.base.tools;

import android.view.View;

public class DoubleClickListener implements View.OnClickListener {

    private final View.OnClickListener mOnClickListener;
    private long old;

    public DoubleClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    @Override
    public void onClick(View view) {
        long now = System.currentTimeMillis();
        if(now - old > 1000) {
            if(mOnClickListener != null) {
                mOnClickListener.onClick(view);
            }
        }
        old = now;
    }
}
