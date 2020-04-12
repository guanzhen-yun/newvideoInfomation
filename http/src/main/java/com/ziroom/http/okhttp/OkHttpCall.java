package com.ziroom.http.okhttp;

import com.ziroom.http.request.IRequest;
import com.ziroom.http.request.call.ICall;
import com.ziroom.http.response.IResponse;
import com.ziroom.http.result.IResult;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author:关震
 * Date:2020/4/11 14:14
 * Description:OkHttpCall
 **/
public class OkHttpCall implements ICall {

    private IRequest request;
    private Call call;

    public OkHttpCall(IRequest request, Call call) {
        this.call = call;
        this.request = request;
    }

    @Override
    public IResponse execute() {
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OkHttpResponse okHttpResponse = new OkHttpResponse(response);
        return okHttpResponse;
    }

    @Override
    public IRequest getRequest() {
        return request;
    }
}
