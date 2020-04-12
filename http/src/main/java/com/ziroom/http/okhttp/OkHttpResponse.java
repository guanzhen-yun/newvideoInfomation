package com.ziroom.http.okhttp;

import com.ziroom.http.response.IResponse;

import java.io.IOException;

import okhttp3.Response;

/**
 * Author:关震
 * Date:2020/4/12 8:13
 * Description:OkHttpResponse
 **/
public class OkHttpResponse implements IResponse {

    private final Response response;

    public OkHttpResponse(Response response) {
        this.response = response;
    }

    @Override
    public String getBodyStr() {
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
