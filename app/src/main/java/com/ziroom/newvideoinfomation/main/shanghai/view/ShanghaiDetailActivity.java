package com.ziroom.newvideoinfomation.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.z.ipc.CallBack;
import com.z.ipc.IConnectListener;
import com.z.ipc.IpcManager;
import com.z.ipc.request.IpcRequest;
import com.z.ipc.result.IResult;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseActivity;
import com.ziroom.newvideoinfomation.base.ViewInject;
import com.ziroom.newvideoinfomation.main.shanghai.dto.ShangHaiDetailBean;
import com.ziroom.newvideoinfomation.main.shanghai.lf.IShanghaiDetailContract;
import com.ziroom.newvideoinfomation.main.shanghai.presenter.ShanghaiDetailPresenter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:关震
 * Date:2020/4/6 14:31
 * Description:ShanghaiDetailActivity
 **/
@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity implements IShanghaiDetailContract.IView {

    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);

    public static String mActivityOptionsCompat = "ShanghaiDetailActivity";

    @BindView(R.id.iv_shanghai_detail)
    ImageView mIvShanghaiDetail;
    @BindView(R.id.tv_crash)
    TextView mTvCrash;
//    private GetProcessReceiver getProcessReceiver;
    //    @BindView(R.id.GLSurfaceView)
//    GLSurfaceView glSurfaceView;

//    private Messenger messenger;

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            Bundle data = msg.getData();
//            Log.e(mActivityOptionsCompat, "processDec= " + data.getString("process"));
//        }
//    };

//    private Messenger messengerClient = new Messenger(handler);

//    private ServiceConnection mConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            messenger = new Messenger(iBinder);
//            Message message = new Message();
//            message.what = MainProcessService.SHANGHAI_DETAIL;
//            message.replyTo = messengerClient;
//            try {
//                messenger.send(message);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName componentName) {
//
//        }
//    };

    @Override
    public void afterBindView() {
//        initViews();
        initAnima();
//        initReceiver();
//        initProcessData();
        initGetNetData();
//        initListener();
//        initPostNetData();
//        initProviderData();
//        initProcessService();
        initIpc();
    }

    private void initIpc() {
        IpcRequest request = new IpcRequest("shanghaiDetail");
        //异步
//        IpcManager.getInstance(this).executeAsync(request, new CallBack() {
//            @Override
//            public void callBack(IResult result) {
//                String data = result.data();
//                Log.e("数据请求", data);
//            }
//        });

        //同步
        IpcManager.getInstance(this).initConnect(new IConnectListener() {
            @Override
            public void onConnect(int connectStatus) {
                if(connectStatus == IpcManager.IConnectStatus.STATUS_BIND) {
                    IResult result = IpcManager.getInstance(ShanghaiDetailActivity.this).executeSync(request);
                    Log.e("数据请求", result.data());
                }
            }
        });
    }

//    private void initProcessService() {
//        Intent intent = new Intent(this, MainProcessService.class);
//        bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
//    }

//    private void initProviderData() {
//        Uri insert = getContentResolver().insert(Uri.parse("content://com.ziroom.newvideoinfomation.process.data"), new ContentValues());
//        Log.e(mActivityOptionsCompat, "processDec= " + insert.toString());
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(getProcessReceiver);
    }

//    private void initReceiver() {
//        getProcessReceiver = new GetProcessReceiver();
////        LocalBroadcastManager.getInstance(this).registerReceiver(); 本地广播
//        registerReceiver(getProcessReceiver, new IntentFilter("beijing_post_process_data"));
//    }

//    private void initProcessData() {
//        Intent intent = new Intent("shanghai_get_process_data");
//        sendBroadcast(intent);
////        String processDec = ProcessDataTest.getInstance().getProcessDec();
////        Log.e(mActivityOptionsCompat, "processDec= " + processDec);
//    }

    private void initPostNetData() {
        OkHttpClient client = new OkHttpClient();//okhttp 配置一些默认
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key", "0f08cd674792667feb5ce236ea028747");
        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/lottery/types")
                .post(builder.build())
                .build();//建造者设计模式
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("initPostNetData", "onFailure" + e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e("initPostNetData", "onResponse" + response.body().string());
            }
        });
    }

    /**
     * 发送网络请求数据
     */
    private void initGetNetData() {
        mIvShanghaiDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getNetData(2);
            }
        });
//        GetXiaoHuaTask task = new GetXiaoHuaTask();
//        task.execute("desc", "1", "2");
//        Object desc = new ShangHaiDetailHttpTask().getXiaoHuaList("desc", "1", "2");
//        if (desc instanceof Response) {
//            Response response = (Response) desc;
//            Log.e("initGetNetData", "onResponse" + response.body().toString());
//        }

        //1. 可以隔离
//        OkHttpClient client = new OkHttpClient();//okhttp 配置一些默认
//        //2.构建请求 1) url  2)参数
//        HttpUrl.Builder builder = HttpUrl.parse("http://v.juhe.cn/joke/content/list.php").newBuilder();
//        builder.addQueryParameter("sort", "desc");
//        builder.addQueryParameter("page", "1");
//        builder.addQueryParameter("pagesize", "2");
//        builder.addQueryParameter("time", "" + System.currentTimeMillis() / 1000);
//        builder.addQueryParameter("key", "bbc57dd5e4f05991aff09eafd2e667e0");
//
//        //3.构建Request
//        Request request = new Request.Builder()
//                .url(builder.build())
//                .get()
//                .build();//建造者设计模式
//        //4.构建Call
//        Call call = client.newCall(request);
//        //5.执行网络请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.e("initGetNetData", "onFailure" + e);
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                Log.e("initGetNetData", "onResponse" + response.body().string());
//            }
//        });
    }

    private void initAnima() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ViewCompat.setTransitionName(mIvShanghaiDetail, mActivityOptionsCompat);
            //延时加载
//            postponeEnterTransition();
            //开启转场动画
            startPostponedEnterTransition();
        }
    }

    /**
     * 用于Android 5.0系统的 界面转场动画 |共享元素动画
     */
    public static void start_5_0(Activity activity, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(activity, ShanghaiDetailActivity.class);
            Pair pair = new Pair(view, mActivityOptionsCompat);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
        }

    }

    @Override
    public void showData(ShangHaiDetailBean data) {
        Toast.makeText(this, data.result.data.get(0).content, Toast.LENGTH_SHORT).show();
    }

//    private class GetProcessReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String processDec = intent.getStringExtra("processDec");
//            Log.e(mActivityOptionsCompat,"processDec= " + processDec);
//        }
//    }

//    private void initListener() {
//        mIvShanghaiDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String s = null;
//                s.toString();
//            }
//        });
//
//        mTvCrash.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String s = null;
//                        s.toString();
//                    }
//                }).start();
//            }
//        });
//    }

//    private void initViews() {
    //        glSurfaceView.setRenderer(new GLSurfaceView.Renderer() {
//            @Override
//            public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
//         //都是子线程回调
//            }
//
//            @Override
//            public void onSurfaceChanged(GL10 gl10, int i, int i1) {
//
//            }
//
//            @Override
//            public void onDrawFrame(GL10 gl10) {
////循环调用 进行渲染
//            }
//        });
//    }
}
