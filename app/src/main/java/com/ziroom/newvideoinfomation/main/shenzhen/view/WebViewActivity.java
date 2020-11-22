package com.ziroom.newvideoinfomation.main.shenzhen.view;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.base.BaseActivity;
import com.ziroom.newvideoinfomation.base.ViewInject;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.activity_webview)
public class WebViewActivity extends BaseActivity {
    //1.webView.loadUrl("http://www.baidu.com")
    //2.webView.loadUrl("file://android_asset/test.html")
    //3.webView.loadData(String data, String mineType, String encoding)

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    public void afterBindView() {
        //在当前webview上加载
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");
    }
}
