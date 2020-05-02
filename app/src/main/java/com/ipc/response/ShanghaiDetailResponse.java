package com.ipc.response;

import android.os.RemoteException;

import com.z.ipc.IClientInterface;
import com.z.ipc.response.BaseResponse;

public class ShanghaiDetailResponse extends BaseResponse {

    public ShanghaiDetailResponse(String requestKey, String params, IClientInterface mClientInterface) {
        super(requestKey, params, mClientInterface);
    }

    public void shanghaiDetail() {
        try {
            mClientInterface.callBack(mRequest, "来自远方的祝福");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //同步
    public String shanghaiDetailS() {
        return "来自远方的祝福";
    }
}
