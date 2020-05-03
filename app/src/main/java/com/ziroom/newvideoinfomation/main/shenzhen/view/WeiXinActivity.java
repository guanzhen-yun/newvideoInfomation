package com.ziroom.newvideoinfomation.main.shenzhen.view;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;
import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseActivity;
import com.ziroom.newvideoinfomation.base.ViewInject;
import com.ziroom.newvideoinfomation.base.tools.FileUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ViewInject(mainlayoutid = R.layout.activity_weixin)
public class WeiXinActivity extends BaseActivity {
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    @Override
    public void afterBindView() {

    }

    @OnClick(R.id.btn_js)
    public void onViewClicked() {
        //解析JS脚本
        //使用 v8 引擎
        V8 v8 = V8.createV8Runtime();
        V8Object v8Object = new V8Object(v8);
        v8.add("wx", v8Object);
        WxTestView wxTestView = new WxTestView();
        v8Object.registerJavaMethod(wxTestView, "textView", "textView", new Class[]{String.class});
        //解析JS文件
        String jsContent = FileUtils.getAssetsContent(WeiXinActivity.this, "js/wx.js");
        //使用v8引擎去 执行js脚本
        v8.executeVoidScript(jsContent);
    }

    public class WxTestView {
        //c++ 调用 Java 方法， 都是通过反射的方式
        public void textView(String text) {
            TextView textView = new TextView(WeiXinActivity.this);
            textView.setText(text);
            llContent.addView(textView);
        }
    }
}
