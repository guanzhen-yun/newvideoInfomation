package com.ziroom.newvideoinfomation.main.beijing;

public class ProcessDataTest {
    private static ProcessDataTest mInstance;

    private String processDec;

    private ProcessDataTest() {
//        Log.e("ShanghaiDetailActivity", "pid= " + android.os.Process.myPid());
    }

    public static synchronized ProcessDataTest getInstance() {
        if(mInstance == null) {
            mInstance = new ProcessDataTest();
        }
        return mInstance;
    }

    public String getProcessDec() {
        return processDec;
    }

    public void setProcessDec(String processDec) {
        this.processDec = processDec;
    }
}
