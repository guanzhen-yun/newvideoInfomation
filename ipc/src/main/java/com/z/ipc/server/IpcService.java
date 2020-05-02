package com.z.ipc.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.z.ipc.IClientInterface;
import com.z.ipc.IServerInterface;
import com.z.ipc.response.ResponseUtil;

public class IpcService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IServerInterface.Stub() {

            private IClientInterface mClientInterface;

            @Override
            public void executeAsync(String requestKey, String requestParams) {
                ResponseUtil.getAsyncResponse(requestKey, requestParams, mClientInterface);
            }

            @Override
            public String executeSync(String requestKey, String requestParams) throws RemoteException {
                return ResponseUtil.getSyncResponse(requestKey, requestParams);
            }

            @Override
            public void registerCallBack(IClientInterface iClientInterface) throws RemoteException {
                this.mClientInterface = iClientInterface;
            }
        };
    }
}
