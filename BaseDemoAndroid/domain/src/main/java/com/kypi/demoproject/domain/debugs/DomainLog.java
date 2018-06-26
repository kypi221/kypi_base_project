package com.kypi.demoproject.domain.debugs;

public interface DomainLog {
    public void d(String msg);
    public void d(String tag, String msg);


    public void i(String msg);
    public void i(String tag, String msg);


    public void e(String msg);
    public void e(String tag, String msg);

    public void longInfo(String tag, String msg);
}
