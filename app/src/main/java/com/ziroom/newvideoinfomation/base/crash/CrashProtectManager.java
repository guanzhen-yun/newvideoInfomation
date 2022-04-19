package com.ziroom.newvideoinfomation.base.crash;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashProtectManager {
  private static CrashProtectManager mInstance;
  private static Context mContext;

  private CrashProtectManager() {}

  public static CrashProtectManager getInstance(Context context) {
    if (mInstance == null) {
      mContext = context.getApplicationContext();
      mInstance = new CrashProtectManager();
    }
    return mInstance;
  }

  public void init() {
    // crash 防护
    Thread.setDefaultUncaughtExceptionHandler(
        new Thread.UncaughtExceptionHandler() {
          @Override
          public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
            //                Log.e("setDefaultUncaughtEx", "是不是发生崩溃了");
            handleFileException(throwable);
            if (thread == Looper.getMainLooper().getThread()) {
              handleMainThread(throwable);
            }
          }
        });
  }

  // 日志文件系统
  private void handleFileException(Throwable throwable) {
    // 通过Throwable生成字符串
    Writer writer = new StringWriter();
    PrintWriter printWriter = new PrintWriter(writer);
    throwable.printStackTrace(printWriter);
    printWriter.close();
    String result = writer.toString();
    // 定义文件名
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD-HH-mm-ss");
    String time = dateFormat.format(new Date());
    String fileName = "crash-" + time + ".txt";
    try {
      if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
        File cacheDir = mContext.getExternalFilesDir(File.separator + "mycrash");
        if (!cacheDir.exists()) {
          cacheDir.mkdirs();
        }
        File cacheFile = new File(cacheDir.getAbsolutePath(), fileName);
        if (!cacheFile.exists()) {
          cacheFile.createNewFile();
        }
        // 把字符串写入到文件
        FileOutputStream outputStream = new FileOutputStream(cacheFile);
        outputStream.write(result.getBytes());
        outputStream.close();
        writer.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void handleMainThread(Throwable throwable) {
    while (true) {
      try {
        Looper.loop();
      } catch (Throwable e) {
        handleFileException(e);
        //                Log.e("handleMainThread", "是不是发生崩溃了");
      }
    }
  }
}
