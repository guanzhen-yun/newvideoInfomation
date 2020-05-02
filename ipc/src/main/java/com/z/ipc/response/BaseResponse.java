package com.z.ipc.response;

import com.z.ipc.IClientInterface;

public class BaseResponse {
    public final String mRequest;
    public final String mParams;
    public final IClientInterface mClientInterface;

    public BaseResponse(String requestKey, String params, IClientInterface mClientInterface) {
        this.mRequest = requestKey;
        this.mParams = params;
        this.mClientInterface = mClientInterface;
    }
}
