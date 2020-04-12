package com.ziroom.newvideoinfomation.main.shanghai.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.ziroom.newvideoinfomation.main.shanghai.module.ShangHaiDetailHttpTask;

import java.io.IOException;

import okhttp3.Response;

/**
 * Author:关震
 * Date:2020/4/11 14:35
 * Description:GetXiaoHuaTask
 **/
public class GetXiaoHuaTask extends AsyncTask<Object, Object, Object> {
    //运行在子线程中
    @Override
    protected Object doInBackground(Object[] objects) {
        Object desc = new ShangHaiDetailHttpTask().getXiaoHuaList((String) objects[0], (String) objects[1], (String) objects[2]);
        return desc;
    }

    //运行在主线程 用于 更新数据
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Response response = (Response) o;
        try {
            Log.e("initGetNetData", "onResponse" + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
