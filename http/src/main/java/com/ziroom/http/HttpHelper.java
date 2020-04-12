package com.ziroom.http;

import com.ziroom.http.okhttp.OkHttpScheduler;
import com.ziroom.http.request.IRequest;
import com.ziroom.http.request.call.ICall;
import com.ziroom.http.result.IResult;

import java.util.Map;

/**
 * Author:关震
 * Date:2020/4/11 13:07
 * Description:HttpHelper
 **/
public class HttpHelper {

    private volatile static HttpScheduler httpScheduler;

    public static HttpScheduler getHttpScheduler() {
        if(httpScheduler == null) {
            synchronized (HttpHelper.class) {
                if(httpScheduler == null) {
                    httpScheduler = new OkHttpScheduler();
                }
            }
        }
        return httpScheduler;
    }

    //TODO 待重构
    protected static <T> IResult<T> execute(IRequest request, Map<String, Object> params) {
        request.setParams(params);
        ICall call = getHttpScheduler().newCall(request);
        return getHttpScheduler().execute(call);
    }
}
