package com.ziroom.http;

import com.ziroom.http.request.IRequest;
import com.ziroom.http.result.IResult;

import java.util.Map;

/**
 * Author:关震
 * Date:2020/4/11 13:04
 * Description:LfHttpServer
 **/
public class LfHttpServer {

    protected <T> IResult<T> execute(IRequest request, Map<String, Object> params) {
        return HttpHelper.execute(request, params);
    }
}
