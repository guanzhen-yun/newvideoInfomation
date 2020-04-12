package com.ziroom.task;

/**
 * Author:关震
 * Date:2020/4/11 15:13
 * Description:TaskHelper
 **/
public class TaskHelper {
    public static void submitTask(ITaskBackground iTaskBackground, ITaskCallback iTaskCallback) {
        AsyncTaskInstance instance = AsyncTaskInstance.getInstance(iTaskBackground, iTaskCallback);
        //构建线程池管理器
        TaskScheduler.getInstance().submit(instance);
    }
}
