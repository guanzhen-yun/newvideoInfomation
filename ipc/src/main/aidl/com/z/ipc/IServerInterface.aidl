// IServerInterface.aidl
package com.z.ipc;

import com.z.ipc.IClientInterface;

interface IServerInterface {
    void executeAsync(String requestKey, String requestParams);

    String executeSync(String requestKey, String requestParams);

    void registerCallBack(IClientInterface iClientInterface);
}
