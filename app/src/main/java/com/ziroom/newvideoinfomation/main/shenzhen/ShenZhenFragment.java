package com.ziroom.newvideoinfomation.main.shenzhen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseFragment;
import com.ziroom.newvideoinfomation.base.ViewInject;

/**
 * Author:关震
 * Date:2020/4/5 19:04
 * Description:ShenZhenFragment
 **/
@ViewInject(mainlayoutid = R.layout.fragment_shenzhen)
public class ShenZhenFragment extends BaseFragment {
    @Override
    public void afterBindView() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser) {
            //加载网络数据
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("ShenZhenFragment", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ShenZhenFragment", "onCreate");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ShenZhenFragment", "onPause");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("ShenZhenFragment", "onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ShenZhenFragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ShenZhenFragment", "onDestroy");
    }

    //FragmentPagerAdapter 会走 onPause onDestroyView
    //FragmentStatePagerAdapter 会走 onPause onDestroyView onDestroy onDetach
}
