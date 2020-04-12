package com.ziroom.http.result;

/**
 * Author:关震
 * Date:2020/4/12 9:10
 * Description:IResultCallBack
 **/
public interface IResultCallBack<T> {
    void onSuccess(IResult<T> t);

    void onFailed(IResult t);
}
