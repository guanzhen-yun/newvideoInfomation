package com.ziroom.task;

/**
 * Author:关震
 * Date:2020/4/11 15:05
 * Description:ITaskCallback
 **/
public interface ITaskCallback<Result> {
    void omComplete(Result o);

    void onException(Throwable throwable);
}
