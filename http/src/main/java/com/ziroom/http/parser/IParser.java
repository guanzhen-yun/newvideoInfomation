package com.ziroom.http.parser;

import com.ziroom.http.request.IRequest;
import com.ziroom.http.response.IResponse;
import com.ziroom.http.result.IResult;

/**
 * Author:关震
 * Date:2020/4/12 8:22
 * Description:IParser
 **/
public interface IParser {
    IResult parseResponse(IRequest request, IResponse response);
}
