package com.ziroom.http.parser;

import com.google.gson.Gson;
import com.ziroom.http.request.IRequest;
import com.ziroom.http.response.IResponse;
import com.ziroom.http.result.IResult;
import com.ziroom.http.result.Result;

import java.lang.reflect.Type;

/**
 * Author:关震
 * Date:2020/4/12 8:22
 * Description:DefaultResultParse
 **/
public class DefaultResultParse implements IParser {
    private static DefaultResultParse mInstance;
    private final Gson mGson;

    private DefaultResultParse() {
        mGson = new Gson();
    }

    public static IParser getInstance() {
        if (mInstance == null) {
            mInstance = new DefaultResultParse();
        }
        return mInstance;
    }

    @Override
    public IResult parseResponse(IRequest request, IResponse response) {
        Type type = request.getType();
        String bodyStr = response.getBodyStr();
        Object object = mGson.fromJson(bodyStr, type);
        return Result.success(object);
    }
}
