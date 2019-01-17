package com.kypi.demoproject.utils;

import android.content.Context;
import android.provider.Settings;

public class AppUtils {

    public static String getDevicesId(Context context){
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceId;
    }
}
