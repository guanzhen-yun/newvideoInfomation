package com.ziroom.newvideoinfomation.base;

import com.ziroom.http.parser.DefaultResultParse;
import com.ziroom.http.request.IRequest;
import com.ziroom.http.annotation.RequestMethod;
import com.ziroom.http.request.LfRequest;

import java.lang.reflect.Type;

/**
 * Author:关震
 * Date:2020/4/11 13:11
 * Description:JHRequest
 **/
public class JHRequest extends LfRequest {
    public static IRequest sendHttp(String path, @RequestMethod int requestMethod, Type type) {
        JHRequest request = new JHRequest();
        request.host = HostManager.jjHost;
        request.path = path;
        request.requestMethod = requestMethod;
        request.type = type;
        request.resultParser = DefaultResultParse.getInstance();
        return request;
    }
}
