package com.kypi.demoproject.app.debug;

import com.kypi.demoproject.domain.debugs.DebugConfig;

public class DebugConfigImpl implements DebugConfig{

    /**
     * Các Biến cần True khi production;
     */
    private static final boolean IS_PRODUCTION = false;

    /**
     * Các Biến cần False khi production
     */
    private static final boolean SIMULATE_SLOW_NET_WORK = true;
    private static final boolean IS_SHOW_LOG_API = true;

    /**
     * Các giá trị khác
     */
    public static final long SIMULATE_BAD_NET_WORK_TIME     = 2000;






    @Override
    public boolean isProduction() {
        return IS_PRODUCTION;
    }

    @Override
    public boolean isSimulateSlowNetWork() {
        return SIMULATE_SLOW_NET_WORK;
    }

    @Override
    public long getSimulateNetWorkTime() {
        return SIMULATE_BAD_NET_WORK_TIME;
    }

    @Override
    public boolean isShowLogAPI() {
        return IS_SHOW_LOG_API;
    }
}
