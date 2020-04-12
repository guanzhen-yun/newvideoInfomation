package com.ziroom.newvideoinfomation.base;

import com.ziroom.http.result.IResult;
import com.ziroom.http.result.IResultCallBack;
import com.ziroom.http.result.Result;
import com.ziroom.task.LfTask;

/**
 * Author:关震
 * Date:2020/4/12 9:03
 * Description:JHTask
 **/
public abstract class JHTask<T> extends LfTask<IResult<T>> implements IResultCallBack<T> {

    @Override
    public void omComplete(IResult<T> iResult) {
        if (iResult != null) {
            if (iResult.isSuccess()) {
                onSuccess(iResult);
            } else {
                onFailed(Result.failed(Result.CODE_505));// 1次失敗
            }
        } else {
            onFailed(Result.failed(Result.CODE_404));//2次失败
        }
    }

    @Override
    public void onFailed(IResult t) {
        switch (t.getCode()) {
            //可以做成统一错误码的处理
            case Result.CODE_404:
                break;
            case Result.CODE_504:
                break;
            case Result.CODE_505:
                break;
        }
    }

    @Override
    public void onException(Throwable throwable) {
        onFailed(Result.failed(Result.CODE_504));//3次失败
    }
}
