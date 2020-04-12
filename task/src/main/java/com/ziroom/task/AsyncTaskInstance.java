package com.ziroom.task;

import com.ziroom.task.tools.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Author:关震
 * Date:2020/4/11 15:18
 * Description:AsyncTaskInstance
 **/
public class AsyncTaskInstance<Result> extends FutureTask<Result> {

    private final ITaskBackground iTaskBackground;
    private final ITaskCallback iTaskCallback;

    public AsyncTaskInstance(final ITaskBackground<Result> iTaskBackground, ITaskCallback<Result> iTaskCallback) {
        super(new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                return iTaskBackground.onBackground();
            }
        });
        this.iTaskBackground = iTaskBackground;
        this.iTaskCallback = iTaskCallback;
    }

    public static AsyncTaskInstance getInstance(ITaskBackground iTaskBackground, ITaskCallback iTaskCallback) {
        return new AsyncTaskInstance(iTaskBackground, iTaskCallback);
    }

    @Override
    protected void done() {
        if (iTaskCallback != null) {
            onComplete();
        }
    }

    //获取FutureTask 执行过程中的异常
    @Override
    protected void setException(final Throwable t) {
        super.setException(t);
        if (iTaskCallback != null) {
            ThreadUtil.postMainThread(new Runnable() {
                @Override
                public void run() {
                    iTaskCallback.onException(t);
                }
            });
        }
    }

    private void onComplete() {
        try {
            final Object object = get();
            if (object != null) {
                ThreadUtil.postMainThread(new Runnable() {
                    @Override
                    public void run() {
                        iTaskCallback.omComplete(object);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
