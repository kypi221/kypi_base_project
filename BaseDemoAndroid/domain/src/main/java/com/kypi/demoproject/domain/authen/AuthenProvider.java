package com.kypi.demoproject.domain.authen;

public interface AuthenProvider {
    public String getAuthen();
    public String getAuthen(String username, String passWord);
}
