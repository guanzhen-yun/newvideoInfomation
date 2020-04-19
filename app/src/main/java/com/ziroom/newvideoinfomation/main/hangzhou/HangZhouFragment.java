package com.ziroom.newvideoinfomation.main.hangzhou;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseFragment;
import com.ziroom.newvideoinfomation.base.ViewInject;
import com.ziroom.newvideoinfomation.main.hangzhou.adapter.HangZhouViewPagerAdapter;
import com.ziroom.newvideoinfomation.main.hangzhou.view.ZhiHuFragment;
import com.ziroom.newvideoinfomation.main.shenzhen.ShenZhenFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Author:关震
 * Date:2020/4/5 19:03
 * Description:HangZhouFragment
 **/
@ViewInject(mainlayoutid = R.layout.fragment_hangzhou)
public class HangZhouFragment extends BaseFragment {
    @BindView(R.id.tl_tablayout)
    TabLayout tlTablayout;
    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;

    @Override
    public void afterBindView() {
        tlTablayout.setupWithViewPager(vpViewpager);
        vpViewpager.setAdapter( new HangZhouViewPagerAdapter(getChildFragmentManager()));
    }
}
