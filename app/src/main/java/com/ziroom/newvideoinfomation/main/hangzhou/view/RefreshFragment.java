package com.ziroom.newvideoinfomation.main.hangzhou.view;

import com.z.refresh.GodRefreshLayout;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseFragment;
import com.ziroom.newvideoinfomation.base.ViewInject;
import com.ziroom.newvideoinfomation.main.hangzhou.refresh.MeiTuanRefreshManager;
import com.ziroom.newvideoinfomation.main.shanghai.dto.ShangHaiDetailBean;
import com.ziroom.newvideoinfomation.main.shanghai.lf.IShanghaiDetailContract;
import com.ziroom.newvideoinfomation.main.shanghai.presenter.ShanghaiDetailPresenter;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment implements IShanghaiDetailContract.IView {
    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);

    @BindView(R.id.god_refresh)
    GodRefreshLayout godRefresh;
//    @BindView(R.id.refresh_recyclerview)
//    RecyclerView mRecyclerView;

    @Override
    public void afterBindView() {
        initRecyclerView();
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        //1.采用默认的方式
//        godRefresh.setRefreshManager();
        //2.要支持用户自定义
        godRefresh.setRefreshManager(new MeiTuanRefreshManager(mContext));
        godRefresh.setRefreshListener(new GodRefreshLayout.RefreshingListener() {
            @Override
            public void onRefreshing() {
//                mPresenter.getNetData(20);
                godRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        godRefresh.refreshOver();
                    }
                }, 2000);
            }
        });
    }

    private void initRecyclerView() {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mPresenter.getNetData(20);
    }


    @Override
    public void showData(ShangHaiDetailBean data) {
//        if(mRecyclerView.getAdapter() == null) {
//            ZhiHuAdapter adapter = new ZhiHuAdapter(data.result.data);
//            mRecyclerView.setAdapter(adapter);
//        }
//        godRefresh.refreshOver();
    }
}
