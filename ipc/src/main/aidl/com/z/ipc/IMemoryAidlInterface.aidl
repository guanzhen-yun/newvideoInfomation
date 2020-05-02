// IMemoryAidlInterface.aidl
package com.z.ipc;
import android.os.ParcelFileDescriptor;
interface IMemoryAidlInterface {
    ParcelFileDescriptor getParcelFileDescriptor();
}
