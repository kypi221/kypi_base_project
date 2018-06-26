package com.kypi.demoproject.domain.debugs;

public interface DebugConfig {

    // môi trường
    public boolean isProduction();

    // Giả lập mạng chậm
    public boolean isSimulateSlowNetWork();
    long getSimulateNetWorkTime();


    public boolean isShowLogAPI();


}
