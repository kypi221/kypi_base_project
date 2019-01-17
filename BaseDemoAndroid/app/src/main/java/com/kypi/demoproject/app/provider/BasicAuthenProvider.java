package com.kypi.demoproject.app.provider;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.kypi.demoproject.domain.authen.AuthenProvider;
import com.kypi.demoproject.utils.BasicAuthenUtil;

import javax.inject.Inject;

public class BasicAuthenProvider implements AuthenProvider {

    private static final String PREF_VALUE_USER_NAME = "PREF_VALUE_USER_NAME";
    private static final String PREF_VALUE_USER_PASSWORD = "PREF_VALUE_USER_PASSWORD";

    protected final SharedPreferences sharedPreferences;
    protected final Gson gson;

    @Inject
    public BasicAuthenProvider(SharedPreferences sharedPreferences,
                               Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    @Override
    public String getAuthen() {
        String userName = sharedPreferences.getString(PREF_VALUE_USER_NAME,"");
        String passWord = sharedPreferences.getString(PREF_VALUE_USER_PASSWORD,"");
        return getAuthen(userName, passWord);
    }

    @Override
    public String getAuthen(String username, String passWord) {
        return BasicAuthenUtil.getAuthen(username, passWord);
    }
}
