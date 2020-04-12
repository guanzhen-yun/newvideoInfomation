package com.ziroom.http.request.call;

import com.ziroom.http.request.IRequest;
import com.ziroom.http.response.IResponse;

/**
 * Author:关震
 * Date:2020/4/11 13:31
 * Description:ICall
 **/
public interface ICall {
    IResponse execute();

    IRequest getRequest();
}
