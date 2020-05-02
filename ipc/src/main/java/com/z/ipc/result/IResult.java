package com.z.ipc.result;

public interface IResult {
    boolean isSuccess();
    int getCode();
    String data();
}
