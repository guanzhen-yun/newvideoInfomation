package com.z.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;

import com.z.ipc.request.IRequest;
import com.z.ipc.result.IResult;
import com.z.ipc.result.IpcResult;
import com.z.ipc.server.IpcService;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class IpcManager {
    private static IpcManager mInstance;
    private final Context mContext;
    //只针对异步请求----------------
    private Set<IRequest> mRequests = new TreeSet<IRequest>();
    private Set<IRequest> mWaitRequests = new TreeSet<IRequest>();
    //------------------------
    private int mConnectStatus = IConnectStatus.STATUS_UNBIND;
    private ServiceConnection mConnection;
    private IServerInterface mServer;
    private IBinder.DeathRecipient mDeathRecipient;
    private IClientInterface.Stub mClientInterface;

    private IConnectListener mConnectionListener;

    public interface IConnectStatus {
        int STATUS_UNBIND = 0;
        int STATUS_BINDING = 1;
        int STATUS_BIND = 2;
    }

    private IpcManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public synchronized static IpcManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new IpcManager(context);
        }
        return mInstance;
    }

    //同步请求
    public IResult executeSync(IRequest request) {
        if (TextUtils.isEmpty(request.getRequestKey())) {
            return IpcResult.getErrorResult();
        }
        //判断服务是否已经连接成功
        if (mConnectStatus != IConnectStatus.STATUS_BIND) {
            connectService();
            return IpcResult.getErrorResult();
        }
        return execute(request);
    }

    //提供给客户端 建立连接的一个方法
    public void initConnect(IConnectListener connectListener) {
        if(mConnectStatus != IConnectStatus.STATUS_BIND) {
            mConnectionListener = connectListener;
            connectService();
        } else {
            connectListener.onConnect(mConnectStatus);
        }
    }

    //异步跨进程通信
    public void executeAsync(IRequest request, CallBack callBack) {
        if (TextUtils.isEmpty(request.getRequestKey()) || mRequests.contains(request)) {
            callBack.callBack(IpcResult.getErrorResult());
            return;
        }
        //保存这个request
        request.addCallBack(callBack);
        mRequests.add(request);
        //判断服务是否已经连接成功
        if (mConnectStatus != IConnectStatus.STATUS_BIND) {
            connectService();
            mWaitRequests.add(request);
            return;
        }

        execute(request);
    }

    //建立IPC 通讯连接
    private void connectService() {
        if (mConnection == null) {
            mConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    mConnectStatus = IConnectStatus.STATUS_BIND;
                    mServer = IServerInterface.Stub.asInterface(iBinder);
                    //往服务端注入接口
                    try {
                        mServer.registerCallBack(mClientInterface);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    //Binder 通信的死亡通知 有重启逻辑
                    try {
                        iBinder.linkToDeath(mDeathRecipient, 0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    //连接成功之后 去把等待的数据请求 发送
                    for (IRequest request : mWaitRequests) {
                        execute(request);
                    }
                    mWaitRequests.clear();
                    if(mConnectionListener != null) {
                        mConnectionListener.onConnect(mConnectStatus);
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    mConnectStatus = IConnectStatus.STATUS_UNBIND;
                    if(mConnectionListener != null) {
                        mConnectionListener.onConnect(mConnectStatus);
                    }
                }
            };
        }
        if (mDeathRecipient == null) {
            mDeathRecipient = new IBinder.DeathRecipient() {

                @Override
                public void binderDied() {
                    mConnectStatus = IConnectStatus.STATUS_UNBIND;
                    //针对异步请求做CallBack处理
                    for (IRequest request : mRequests) {
                        request.getCallBack().callBack(IpcResult.getErrorResult());
                    }
                    mRequests.clear();
                }
            };
        }

        if (mClientInterface == null) {
            mClientInterface = new IClientInterface.Stub() {

                @Override
                public void callBack(String requestKey, String resultStr) throws RemoteException {
                    Iterator<IRequest> iterator = mRequests.iterator();
                    while (iterator.hasNext()) {
                        IRequest next = iterator.next();
                        if (TextUtils.equals(next.getRequestKey(), requestKey)) {
                            next.getCallBack().callBack(IpcResult.getSuccessResult(resultStr));
                            mRequests.remove(next);
                            return;
                        }
                    }
                }
            };
        }
        Intent intent = new Intent(mContext, IpcService.class);
        mContext.bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        mConnectStatus = IConnectStatus.STATUS_BINDING;
    }

    //实际 跨进程通讯的方法
    private IResult execute(IRequest request) {
        if (request.getCallBack() != null) {
            try {
                mServer.executeAsync(request.getRequestKey(), request.getParams());
            } catch (RemoteException e) {
                request.getCallBack().callBack(IpcResult.getErrorResult());
            }
        } else {
            try {
                String result = mServer.executeSync(request.getRequestKey(), request.getParams());
                return IpcResult.getSuccessResult(result);
            } catch (RemoteException e) {
                return IpcResult.getErrorResult();
            }
        }
        return IpcResult.getErrorResult();
    }
}
