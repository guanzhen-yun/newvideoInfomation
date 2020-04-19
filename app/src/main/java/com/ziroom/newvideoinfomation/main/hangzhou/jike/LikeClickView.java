package com.ziroom.newvideoinfomation.main.hangzhou.jike;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.main.tools.SystemUtil;

public class LikeClickView extends View {

    private boolean isLike;
    private Bitmap likeBitmap;
    private Bitmap unLikeBitmap;
    private Bitmap shiningBitmap;
    private Paint bitmapPaint;
    private int left;
    private int top;
    private float handScale = 1.0f;
    private float centerY;
    private float centerX;

    public LikeClickView(Context context) {
        this(context, null);
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JiKeLikeView);
        isLike = typedArray.getBoolean(R.styleable.JiKeLikeView_is_like, false);
        typedArray.recycle();
        init();
    }

    private void init() {
        Resources resources = getResources();
        likeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like);
        unLikeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_unlike);
        shiningBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like_shining);
        bitmapPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = 0;
        int measureHeight = 0;
        int maxHeight = unLikeBitmap.getHeight() + SystemUtil.dp2px(getContext(), 20);
        int maxWidth = unLikeBitmap.getWidth() + SystemUtil.dp2px(getContext(), 30);

        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (wMode != MeasureSpec.EXACTLY) {
            //测量模式未指定 如果有背景 背景多大 我们这个控件就有多大
            int suggestedMinimumWidth = getSuggestedMinimumWidth();
            if (suggestedMinimumWidth == 0) {
                measureWidth = maxWidth;
            } else {
                measureWidth = Math.min(suggestedMinimumWidth, maxWidth);
            }
        } else {
            //测量模式指定 根据用户定义大小来判断
            measureWidth = Math.min(maxWidth, widthSize);
        }

        if (hMode != MeasureSpec.EXACTLY) {
            //测量模式未指定 如果有背景 背景多大 我们这个控件就有多大
            int suggestedMinimumHeight = getSuggestedMinimumHeight();
            if (suggestedMinimumHeight == 0) {
                measureHeight = maxHeight;
            } else {
                measureHeight = Math.min(suggestedMinimumHeight, maxHeight);
            }
        } else {
            measureHeight = Math.min(maxHeight, heightSize);
        }

        setMeasuredDimension(measureWidth, measureHeight);
        getPadding(measureWidth, measureHeight);
    }

    private void getPadding(int measureWidth, int measureHeight) {
        int bitmapWidth = likeBitmap.getWidth();
        int bitmapHeight = likeBitmap.getHeight();
        left = (measureWidth - bitmapWidth) / 2;
        top = (measureHeight - bitmapHeight) / 2;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        centerX = width / 2.0f;
        centerY = height / 2.0f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap handBitmap = isLike ? likeBitmap : unLikeBitmap;
        //使用canvas scale 及其他的效果方法时 必须先调用save 然后再调用restore (这两个方法成对出现)
        canvas.save();
        canvas.scale(handScale, handScale, centerX, centerY);
        canvas.drawBitmap(handBitmap, left, top, bitmapPaint);
        canvas.restore();
        if (isLike) {
            canvas.drawBitmap(shiningBitmap, left + 10, 0, bitmapPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onClick();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    //当这个自定义View 从界面 脱离消失的时候
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        likeBitmap.recycle();
        unLikeBitmap.recycle();
        shiningBitmap.recycle();
    }

    //待完善 动画的处理
    private void onClick() {
        isLike = !isLike;
//        ObjectAnimator handScale = ObjectAnimator.ofFloat(this, "handScale", 1.0f, 0.8f, 1.0f);
//        handScale.setDuration(250);
//        handScale.start();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 0.8f, 1.0f);
        valueAnimator.setDuration(250);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                handScale = animatedValue;
                invalidate();
            }
        });
        invalidate();
    }

    /**
     * 使用ObjectAnimator 系统会自动调用 该属性的 Set方法
     *
     * @param value
     */
    public void setHandScale(float value) {
        this.handScale = value;
        invalidate();
    }
}
