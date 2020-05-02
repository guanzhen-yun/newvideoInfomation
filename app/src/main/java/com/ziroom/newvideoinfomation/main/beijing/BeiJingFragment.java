package com.ziroom.newvideoinfomation.main.beijing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.ziroom.newvideoinfomation.BuildConfig;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseFragment;
import com.ziroom.newvideoinfomation.base.ViewInject;
import com.ziroom.newvideoinfomation.main.shanghai.view.ShanghaiDetailActivity;

import butterknife.BindView;

/**
 * Author:关震
 * Date:2020/4/5 19:04
 * Description:BeiJingFragment
 **/
@ViewInject(mainlayoutid = R.layout.fragment_beijing)
public class BeiJingFragment extends BaseFragment {

    @BindView(R.id.bt_paly)
    Button tvClick;
    @BindView(R.id.permission)
    Button btClick;

//    private ProcessDataReceiver processDataReceiver;

    @Override
    public void afterBindView() {
        if(Build.VERSION.SDK_INT  > Build.VERSION_CODES.O) {
            mContext.startForegroundService(new Intent(mContext, MainProcessService.class));
        } else {
            mContext.startService(new Intent(mContext, MainProcessService.class));
        }
        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //去上海
                ProcessDataTest.getInstance().setProcessDec("你好，我来自北京");
                ShanghaiDetailActivity.start_5_0(getActivity(), tvClick);
            }
        });
//        processDataReceiver = new ProcessDataReceiver();
//        getActivity().registerReceiver(processDataReceiver, new IntentFilter("shanghai_get_process_data"));

        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    int state = getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                    if(state == PackageManager.PERMISSION_GRANTED) {
                        Log.e("BeiJingFragment", "权限已经申请");
                    } else {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        Log.e("BeiJingFragment", "权限没有申请");
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("BeiJingFragment", grantResults[0] + " ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.stopService(new Intent(mContext, MainProcessService.class));
//        getActivity().unregisterReceiver(processDataReceiver);
    }

//    private class ProcessDataReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String processDec = ProcessDataTest.getInstance().getProcessDec();
//            Intent postIntent = new Intent("beijing_post_process_data");
//            postIntent.putExtra("processDec", processDec);
//            getActivity().sendBroadcast(postIntent);
//        }
//    }
}
