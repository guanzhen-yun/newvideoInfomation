package com.ziroom.http.result;

/**
 * Author:关震
 * Date:2020/4/12 8:02
 * Description:IResult 所有IResponse解析后的结果
 **/
public interface IResult<T> {
    boolean isSuccess();
    int getCode();

    T data();
}
