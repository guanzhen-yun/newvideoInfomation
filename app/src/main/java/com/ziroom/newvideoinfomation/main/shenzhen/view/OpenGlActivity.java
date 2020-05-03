package com.ziroom.newvideoinfomation.main.shenzhen.view;

import android.opengl.GLSurfaceView;
import android.widget.TextView;

import com.z.opengl.OpenGlManager;
import com.z.videoinfomationndk.MainActivity;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseActivity;
import com.ziroom.newvideoinfomation.base.ViewInject;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.activity_opengl)
public class OpenGlActivity extends BaseActivity {
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.gl_surface_view)
    GLSurfaceView glSurfaceView;

    static {
        System.loadLibrary("native-lib");
    }

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
}
