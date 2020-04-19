package com.ziroom.newvideoinfomation.main.hangzhou.view;

import com.z.refresh.GodRefreshLayout;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseFragment;
import com.ziroom.newvideoinfomation.base.ViewInject;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment {
    @BindView(R.id.god_refresh)
    GodRefreshLayout godRefresh;

    @Override
    public void afterBindView() {
        godRefresh.setRefreshManager();
    }
}
