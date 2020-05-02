package com.z.ipc.response;

import com.z.ipc.IClientInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ResponseUtil {
    /**
     * 反射调用的类 必须在 com.ipc.response包名下面
     */
    //异步
    public static void getAsyncResponse(String requestKey, String requestParams, IClientInterface mClientInterface) {
        StringBuilder stringBuilder = new StringBuilder("com.ipc.response.");
        String first = requestKey.substring(0, 1);
        String second = requestKey.substring(1);
        stringBuilder.append(first.toUpperCase()).append(second).append("Response");
        //通过反射的方式调用的 宿主的类里面
        try {
            Class<?> clazz = Class.forName(stringBuilder.toString());
            Constructor<?> constructor = clazz.getConstructor(String.class, String.class, IClientInterface.class);
            Object object = constructor.newInstance(requestKey, requestParams, mClientInterface);
            Method method = clazz.getMethod(requestKey);
            method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //同步
    public static String getSyncResponse(String requestKey, String requestParams) {
        StringBuilder stringBuilder = new StringBuilder("com.ipc.response.");
        String first = requestKey.substring(0, 1);
        String second = requestKey.substring(1);
        stringBuilder.append(first.toUpperCase()).append(second).append("Response");
        //通过反射的方式调用的 宿主的类里面
        try {
            Class<?> clazz = Class.forName(stringBuilder.toString());
            Constructor<?> constructor = clazz.getConstructor(String.class, String.class, IClientInterface.class);
            Object object = constructor.newInstance(requestKey, requestParams, null);
            Method method = clazz.getMethod(requestKey + "S");
            return (String) method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
