package com.z.ipc;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;

import androidx.annotation.Nullable;

import com.z.ipc.server.MemoryFetchService;

import java.io.FileDescriptor;
import java.io.FileInputStream;

public class MemoryTestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MemoryTestActivity.this, MemoryFetchService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                byte[] content = new byte[10];
                IMemoryAidlInterface iMemoryAidlInterface = IMemoryAidlInterface.Stub.asInterface(iBinder);
                try {
                    ParcelFileDescriptor parcelFileDescriptor = iMemoryAidlInterface.getParcelFileDescriptor();
                    FileDescriptor descriptor = parcelFileDescriptor.getFileDescriptor();
                    FileInputStream fileInputStream = new FileInputStream(descriptor);
                    int read = fileInputStream.read(content);
                    System.out.println(new String(content));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, Service.BIND_AUTO_CREATE);
    }
}
