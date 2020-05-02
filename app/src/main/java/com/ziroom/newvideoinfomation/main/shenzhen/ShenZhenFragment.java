package com.ziroom.newvideoinfomation.main.shenzhen;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.z.opengl.OpenGlManager;
import com.z.videoinfomationndk.MainActivity;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseFragment;
import com.ziroom.newvideoinfomation.base.ViewInject;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;

/**
 * Author:关震
 * Date:2020/4/5 19:04
 * Description:ShenZhenFragment
 **/
@ViewInject(mainlayoutid = R.layout.fragment_shenzhen)
public class ShenZhenFragment extends BaseFragment {

    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.gl_surface_view)
    GLSurfaceView glSurfaceView;

    @Override
    public void afterBindView() {
        tvPosition.setText(MainActivity.stringFromJNI());
        glSurfaceView.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
                //为缓冲区 设置清除颜色的值 相当于初始化
                OpenGlManager.onSurfaceCreated();
            }

            @Override
            public void onSurfaceChanged(GL10 gl10, int width, int height) {
                //设置 视图 大小
                OpenGlManager.onSurfaceChanged(width, height);
            }

            //绘制的时候 每一帧 都会被系统调用 在Android中 默认最高绘制效率 为1秒 60帧
            @Override
            public void onDrawFrame(GL10 gl10) {
                //设置 色彩
                OpenGlManager.onDrawFrame();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
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
