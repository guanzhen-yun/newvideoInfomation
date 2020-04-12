package com.ziroom.http.request;

import com.ziroom.http.annotation.RequestMethod;
import com.ziroom.http.parser.IParser;
import com.ziroom.http.request.host.IHost;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Author:关震
 * Date:2020/4/11 13:14
 * Description:LfRequest
 **/
public class LfRequest implements IRequest {
    protected String path;

    protected IHost host;

    protected Map<String, Object> params;

    protected Type type;

    protected IParser resultParser;

    @RequestMethod
    protected int requestMethod;

    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public int getRequestMethod() {
        return requestMethod;
    }

    @Override
    public IHost getHost() {
        return host;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public IParser getParser() {
        return resultParser;
    }

    @Override
    public Type getType() {
        return type;
    }
}
