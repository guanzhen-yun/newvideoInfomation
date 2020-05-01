package com.ziroom.newvideoinfomation.base.tools;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

public class AnimationUtil {
    /**
     * x轴方向的属性动画
     */
    public static void startTranslationXAnim(View target, float positionStart, float positionEnd, AnimatorListenerAdapter listener) {
        ObjectAnimator titleAnim = ObjectAnimator.ofFloat(target, "translationX", positionStart, positionEnd);
        titleAnim.setDuration(1000);
        titleAnim.start();
        if(listener != null) {
            titleAnim.addListener(listener);
        }
    }
}
