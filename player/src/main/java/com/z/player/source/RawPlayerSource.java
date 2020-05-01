package com.z.player.source;

import java.io.File;

public class RawPlayerSource implements IPlayerSource {
    private String url;
    private int resId;

    //"android.resource://" + getPackageName() + File.separator + R.raw.splash
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    public IPlayerSource setPath(String packageName, int rawId) {
        setUrl("android.resource://" + packageName + File.separator + rawId);
        setResId(rawId);
        return this;
    }

    private void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public int getResId() {
        return resId;
    }
}
