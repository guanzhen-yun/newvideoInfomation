package com.ziroom.newvideoinfomation.main.shanghai.dto;

import java.util.ArrayList;

/**
 * Author:关震
 * Date:2020/4/6 12:47
 * Description:ShanghaiBean
 **/
public class ShanghaiBean {
    public int getmItemType() {
        return mItemType;
    }

    public ShanghaiBean setmItemType(int mItemType) {
        this.mItemType = mItemType;
        return this;
    }

    public String getmDec() {
        return mDec;
    }

    public ShanghaiBean setmDec(String mDec) {
        this.mDec = mDec;
        return this;
    }

    public boolean isShowImg() {
        return isShowImg;
    }

    public ShanghaiBean setShowImg(boolean showImg) {
        isShowImg = showImg;
        return this;
    }

    public ArrayList<ShanghaiBean> getData() {
        return data;
    }

    public ShanghaiBean setData(ArrayList<ShanghaiBean> data) {
        this.data = data;
        return this;
    }

    private int mItemType;
    private String mDec;
    private boolean isShowImg;
    private ArrayList<ShanghaiBean> data;

    public interface IShanghaiItemType {
        int VERTICAL = 0;
        int HORIZANTAL = 1;
    }
}
