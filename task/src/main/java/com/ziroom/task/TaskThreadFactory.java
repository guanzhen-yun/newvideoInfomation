package com.ziroom.task;

import java.util.concurrent.ThreadFactory;

/**
 * Author:关震
 * Date:2020/4/11 22:21
 * Description:TaskThreadFactory
 **/
public class TaskThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "task_thread_pool");
    }
}
