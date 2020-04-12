package com.ziroom.http;

import com.ziroom.http.parser.IParser;
import com.ziroom.http.request.IRequest;
import com.ziroom.http.request.call.ICall;
import com.ziroom.http.response.IResponse;
import com.ziroom.http.result.IResult;

/**
 * Author:关震
 * Date:2020/4/11 13:33
 * Description:HttpScheduler
 **/
public abstract class HttpScheduler {

    public abstract ICall newCall(IRequest request);

    public IResult execute(ICall call) {
        //IResponse 和IResult 进行一个转换
        IResponse iResponse = call.execute();
        IRequest request = call.getRequest();
        IParser parser = request.getParser();
        return parser.parseResponse(request, iResponse);
    }
}
