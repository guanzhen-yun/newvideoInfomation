package com.z.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class GodRefreshLayout extends LinearLayout {

    private BaseRefreshManager mRefreshManager;
    private Context mContext;
    private View mHeadView;
    private int mHeadViewHeight;
    private int minHeadViewHeight;//头部布局最小一个高度
    private int maxHeadViewHeight;//头部布局最大一个高度

    public GodRefreshLayout(Context context) {
        this(context, null);
    }

    public GodRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GodRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
    }

    /**
     * 开启下拉刷新 下拉刷新的效果 是默认的
     */
    public void setRefreshManager() {
        mRefreshManager = new DefaultRefreshManager(mContext);
        initHeaderView();
    }

    /**
     * 开启下拉刷新 使用用户自定义的下拉刷新效果
     */
    public void setRefreshManager(BaseRefreshManager manager) {
        mRefreshManager = manager;
        initHeaderView();
    }

    private void initHeaderView() {
        setOrientation(VERTICAL);
        mHeadView = mRefreshManager.getHeaderView();
        mHeadView.measure(0, 0);
        mHeadViewHeight = mHeadView.getMeasuredHeight();
        minHeadViewHeight = -mHeadViewHeight;
        maxHeadViewHeight = (int)(mHeadViewHeight * 0.3f);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeadViewHeight);
        params.topMargin = -mHeadViewHeight;
        addView(mHeadView, 0, params);
    }

    private int downY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int)event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int)event.getY();
                int dy = moveY - downY;
                if(dy > 0) {
                    LayoutParams layoutParams = (LayoutParams)mHeadView.getLayoutParams();
                    //阻尼效果
                    layoutParams.topMargin = (int)Math.min(dy/1.8f + minHeadViewHeight, maxHeadViewHeight);
                    mHeadView.setLayoutParams(layoutParams);
                }
                return true;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
