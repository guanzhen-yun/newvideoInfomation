package com.ziroom.http.request;

import com.ziroom.http.parser.IParser;
import com.ziroom.http.request.host.IHost;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Author:关震
 * Date:2020/4/11 13:06
 * Description:IRequest
 **/
public interface IRequest {
    void setParams(Map<String, Object> params);

    Map<String, Object> getParams();

    int getRequestMethod();

    IHost getHost();

    String getPath();

    IParser getParser();

    Type getType();
}
