package com.z.ipc.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.z.ipc.IMemoryAidlInterface;

import java.io.File;
import java.io.FileDescriptor;
import java.lang.reflect.Method;

public class MemoryFetchService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    static class MemoryFetchStub extends IMemoryAidlInterface.Stub {

        @Override
        public ParcelFileDescriptor getParcelFileDescriptor() throws RemoteException {
            MemoryFile memoryFile = null;
            try {
                memoryFile = new MemoryFile("test_memory", 1024);
                memoryFile.getOutputStream().write(new byte[]{1,2,3,4,5});
                Method method = MemoryFile.class.getDeclaredMethod("getFileDescriptor");
                FileDescriptor des = (FileDescriptor) method.invoke(memoryFile);
                return ParcelFileDescriptor.dup(des);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
