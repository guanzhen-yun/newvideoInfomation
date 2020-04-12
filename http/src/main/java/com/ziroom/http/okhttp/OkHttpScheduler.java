package com.ziroom.http.okhttp;

import com.ziroom.http.HttpScheduler;
import com.ziroom.http.annotation.RequestMethod;
import com.ziroom.http.https.Https;
import com.ziroom.http.request.IRequest;
import com.ziroom.http.request.call.ICall;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:关震
 * Date:2020/4/11 13:33
 * Description:OkHttpScheduler
 **/
public class OkHttpScheduler extends HttpScheduler {

    private OkHttpClient client;

    @Override
    public ICall newCall(IRequest request) {
        Map<String, Object> params = request.getParams();
        int requestMethod = request.getRequestMethod();
        Request.Builder requestUrlBuilder = new Request.Builder();
        switch (requestMethod) {
            case RequestMethod.Get:
                //拼接Get请求的url host + path
                StringBuilder urlStrBuilder = new StringBuilder(request.getHost().getHost());
                urlStrBuilder.append(request.getPath());
                HttpUrl.Builder urlBuilder = HttpUrl.parse(urlStrBuilder.toString()).newBuilder();
                if(params != null && params.size() > 0) {
                    Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> next = iterator.next();
                        //TODO 待重构 这里涉及对象如何转成String字符串
                        urlBuilder.addQueryParameter(next.getKey(), String.valueOf(next.getValue()));
                    }
                }
                requestUrlBuilder.get().url(urlBuilder.build());
                break;
                case RequestMethod.Post:
                    //TODO 留給大家去完善
                    break;
        }
        Request okHttpRequest = requestUrlBuilder.build();
        Call call = getClient().newCall(okHttpRequest);
        OkHttpCall okHttpCall = new OkHttpCall(request, call);
        return okHttpCall;
    }

    private OkHttpClient getClient() {
        if(client == null) {
            client = new OkHttpClient();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.sslSocketFactory(Https.getSSLSocketFactory());//https
//            builder.hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String s, SSLSession sslSession) {
//                    return true;
//                }
//            });
////            builder.cache(new Cache());//添加缓存
//            client = builder.build();
//            builder.addInterceptor(new Interceptor() {//拦截器
//                @NotNull
//                @Override
//                public Response intercept(@NotNull Chain chain) throws IOException {
//                    Request request = chain.request();
//                    String method = request.method();
//                    Response response = chain.proceed(request);
//                    response.body().string();
//                    return response;
//                }
//            })
//            builder.dns(new Dns() { //dns解析
//                @NotNull
//                @Override
//                public List<InetAddress> lookup(@NotNull String host) throws UnknownHostException {
//                    String hosts = "www.juhe.com";
//                    List<String> ipList = Dns.getIds(hosts);
//                    for (int i = 0; i < ipList.size(); i++) {
//                        String ip = ipList.get(i);
//                        InetAddress byName = InetAddress.getByName(ip);
//
//                    }
//                    return null;
//                }
//            });
        }
        return client;
    }
}
