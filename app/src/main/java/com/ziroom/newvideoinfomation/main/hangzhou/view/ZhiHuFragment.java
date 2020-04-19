package com.ziroom.newvideoinfomation.main.hangzhou.view;

import android.view.animation.AnimationUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseFragment;
import com.ziroom.newvideoinfomation.base.ViewInject;
import com.ziroom.newvideoinfomation.main.hangzhou.adapter.ZhiHuAdapter;
import com.ziroom.newvideoinfomation.main.shanghai.dto.ShangHaiDetailBean;
import com.ziroom.newvideoinfomation.main.shanghai.presenter.IShanghaiDetailContract;
import com.ziroom.newvideoinfomation.main.shanghai.presenter.ShanghaiDetailPresenter;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_zhihu)
public class ZhiHuFragment extends BaseFragment implements IShanghaiDetailContract.IView {
    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.zhihu_app_barlayout)
    AppBarLayout zhihuAppBarlayout;
    @BindView(R.id.zhihu_recyclerview)
    RecyclerView zhihuRecyclerview;

    @Override
    public void afterBindView() {
        zhihuRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        zhihuRecyclerview.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zhihu_recyclerview_show));
        mPresenter.getNetData(20);
    }

    @Override
    public void showData(ShangHaiDetailBean data) {
        if (zhihuRecyclerview.getAdapter() == null) {
            ZhiHuAdapter adapter = new ZhiHuAdapter(data.result.data);
            zhihuRecyclerview.setAdapter(adapter);
        }
    }
}
