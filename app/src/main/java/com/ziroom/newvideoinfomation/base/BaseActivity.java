package com.ziroom.newvideoinfomation.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ziroom.newvideoinfomation.mvp.view.LifeCircleMvpActivity;

import butterknife.ButterKnife;

/**
 * Author:关震
 * Date:2020/4/4 21:12
 * Description:BaseActivity
 **/
public abstract class BaseActivity extends LifeCircleMvpActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if(annotation != null) {
            int mainlayoutid = annotation.mainlayoutid();
            if(mainlayoutid > 0) {
                setContentView(mainlayoutid);
                bindView();
                afterBindView();
            } else {
                throw new RuntimeException("mainlayoutid < 0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
    }

    //模板方法 设计模式
    public abstract void afterBindView();

    //View 的依赖注入绑定
    private void bindView() {
        ButterKnife.bind(this);
    }
}
