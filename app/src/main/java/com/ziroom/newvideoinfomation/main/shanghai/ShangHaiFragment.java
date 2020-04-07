package com.ziroom.newvideoinfomation.main.shanghai;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseFragment;
import com.ziroom.newvideoinfomation.base.ViewInject;
import com.ziroom.newvideoinfomation.main.shanghai.adapter.ShanghaiAdapter;
import com.ziroom.newvideoinfomation.main.shanghai.dto.ShanghaiDataManager;

import butterknife.BindView;

/**
 * Author:关震
 * Date:2020/4/5 19:01
 * Description:ShangHaiFragment
 **/
@ViewInject(mainlayoutid = R.layout.fragment_shanghai)
public class ShangHaiFragment extends BaseFragment {

    @BindView(R.id.tv_shanghai_welcome)
    TextView mTvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingtoolbarlayout)
    CollapsingToolbarLayout mShanghaiCollapsingtoolbarlayout;
    @BindView(R.id.shanghai_app_barlayout)
    AppBarLayout mShanghaiAppBarlayout;
    @BindView(R.id.shanghai_recyclerview)
    RecyclerView mShanghaiRecyclerview;

    @Override
    public void afterBindView() {
        initRecyclerView();
        initListener();
    }

    private void initRecyclerView() {
        mShanghaiRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mShanghaiRecyclerview.setAdapter(new ShanghaiAdapter(getActivity(), ShanghaiDataManager.getData(), false));
    }

    private void initListener() {
        mShanghaiAppBarlayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (-verticalOffset < appBarLayout.getMeasuredHeight() / 2) {
                mTvShanghaiWelcome.setVisibility(View.INVISIBLE);
            } else {
                mTvShanghaiWelcome.setVisibility(View.VISIBLE);
            }
        });
    }
}
