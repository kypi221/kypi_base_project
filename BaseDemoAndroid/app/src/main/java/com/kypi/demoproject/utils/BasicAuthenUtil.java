package com.kypi.demoproject.utils;

import android.util.Base64;

/**
 * Created by Khoa on 8/9/2017.
 */

public class BasicAuthenUtil {

    public static final String getAuthen(String userId, String sessionId){
        final String loginInfo = userId + ":" +sessionId;
        return  "Basic " + Base64.encodeToString(loginInfo.getBytes(), Base64.NO_WRAP);
    }

    public static final String getAuthen(int userId, String sessionId){
        final String loginInfo = userId + ":" +sessionId;
        return  "Basic " + Base64.encodeToString(loginInfo.getBytes(), Base64.NO_WRAP);
    }
}
