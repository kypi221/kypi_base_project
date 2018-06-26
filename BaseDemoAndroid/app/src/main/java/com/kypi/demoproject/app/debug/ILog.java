package com.kypi.demoproject.app.debug;

import android.util.Log;

public class ILog {


    private static final boolean SHOW_LOG = true;
    private static final boolean SHOW_REQUEST_URL = true;
    private static final boolean SHOW_RESPONSE = true;

    private static final String TAG = "KhoaHM ";
    private static final String REQUEST_TAG = "IRequest ";
    private static final String RESPONSE_TAG = "IRespone ";


    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // $$$$$$$$$$$$$$$$      Defaults Log     $$$$$$$$$$$$$$$$$$$$$
    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // ========================
    // Log d
    // ========================
    public static void d(String msg){
        if(SHOW_LOG){
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg){
        if(SHOW_LOG){
            Log.d(TAG + tag, msg);
        }
    }

    // ========================
    // Log i
    // ========================
    public static void i(String msg){
        if(SHOW_LOG){
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag, String msg){
        if(SHOW_LOG){
            Log.i(TAG + tag, msg);
        }
    }

    // ========================
    // Log e
    // ========================
    public static void e(String msg){
        if(SHOW_LOG){
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg){
        if(SHOW_LOG){
            Log.e(TAG + tag, msg);
        }
    }








    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // $$$$$$$$$$$$$$$$      Defaults Log     $$$$$$$$$$$$$$$$$$$$$
    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // ========================
    // Log SHOW_REQUEST_URL
    // ========================
    public static void api_requestURL(String msg){
        if(SHOW_REQUEST_URL){
            Log.d(REQUEST_TAG, msg);
        }
    }

    public static void api_requestURL(String tag, String msg){
        if(SHOW_REQUEST_URL){
            Log.d(REQUEST_TAG + tag, msg);
        }
    }



    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // $$$$$$$$$$$$$$$$      Defaults Log     $$$$$$$$$$$$$$$$$$$$$
    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // ========================
    // Log SHOW_RESPONSE
    // ========================
    public static void api_response(String msg){
        if(SHOW_RESPONSE){
            longInfo(RESPONSE_TAG, msg);
        }
    }

    public static void api_response(String tag, String msg){
        if(SHOW_RESPONSE){
            longInfo(RESPONSE_TAG + tag, msg);
        }
    }





    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // $$$$$$$$$$$$$$$$      Defaults Log     $$$$$$$$$$$$$$$$$$$$$
    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public static void longInfo(String tag ,String str) {
        if(str.length() > 3000) {
            Log.i(tag, str.substring(0, 3000));
            longInfo(tag, str.substring(3000));
        } else {
            Log.i(tag, str);
            Log.i(tag, "Line Space");
        }
    }

    public static void e(Exception e) {
        if(SHOW_LOG){
            e.printStackTrace();
        }
    }
}
