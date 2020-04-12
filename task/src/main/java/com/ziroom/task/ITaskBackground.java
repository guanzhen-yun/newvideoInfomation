package com.ziroom.task;

/**
 * Author:关震
 * Date:2020/4/11 15:05
 * Description:ITaskBackground
 **/
public interface ITaskBackground<Result> {
    Result onBackground();
}
