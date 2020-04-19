package com.z.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseRefreshManager {

    public final LayoutInflater mLayoutInflater;

    public BaseRefreshManager(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }
    public abstract View getHeaderView();
}
