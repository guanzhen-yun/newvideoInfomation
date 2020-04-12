package com.ziroom.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import androidx.annotation.NonNull;

import com.ziroom.task.tools.ThreadUtil;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Author:关震
 * Date:2020/4/11 16:11
 * Description:TaskScheduler
 **/
public class TaskScheduler {

    private final PriorityThreadPoolExecutor executor;

    interface ITaskSchedulerType {
        int SUBMIT_TASK = 0;
    }

    private static TaskScheduler instance;
    private final Handler handler;
    private int COREPOOLSIZE = ThreadUtil.CPU_NUM + 1;
    private int MAXIMUMPOOLSIZE = COREPOOLSIZE * 3;
    private int KEEPALIVETIME = 1;

    private TaskScheduler() {
        //用于消息调度的线程
        HandlerThread handlerThread = new HandlerThread("task-thread");
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            //HandlerThread handleMessage 运行在子线程
            @Override
            public boolean handleMessage(@NonNull Message message) {
                switch (message.what) {
                    case ITaskSchedulerType.SUBMIT_TASK:
                        doSubmitTask((AsyncTaskInstance) message.obj);
                        break;
                }
                return false;
            }
        });
        //创建一个线程池
        BlockingQueue<Runnable> poolQueue = new LinkedBlockingDeque<>();
        executor = new PriorityThreadPoolExecutor(
                COREPOOLSIZE,
                MAXIMUMPOOLSIZE,
                KEEPALIVETIME,
                TimeUnit.MINUTES,
                poolQueue,
                new TaskThreadFactory()
        );
    }

    private void doSubmitTask(AsyncTaskInstance taskInstance) {
        executor.submit(taskInstance);
    }

    public static TaskScheduler getInstance() {
        if (instance == null) {
            instance = new TaskScheduler();
        }
        return instance;
    }

    public void submit(AsyncTaskInstance instance) {
        handler.sendMessage(handler.obtainMessage(ITaskSchedulerType.SUBMIT_TASK, instance));
    }
}
