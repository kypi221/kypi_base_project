package com.kypi.demoproject.app.debug;

import com.kypi.demoproject.domain.debugs.DomainLog;

public class DomainLogImpl implements DomainLog {
    @Override
    public void d(String msg) {
        ILog.d(msg);
    }

    @Override
    public void d(String tag, String msg) {
        ILog.d(tag, msg);
    }

    @Override
    public void i(String msg) {
        ILog.i(msg);
    }

    @Override
    public void i(String tag, String msg) {
ILog.i(tag, msg);
    }

    @Override
    public void e(String msg) {
        ILog.e(msg);
    }

    @Override
    public void e(String tag, String msg) {
        ILog.e(tag, msg);
    }

    @Override
    public void longInfo(String tag, String msg) {
        ILog.longInfo(tag, msg);
    }
}
