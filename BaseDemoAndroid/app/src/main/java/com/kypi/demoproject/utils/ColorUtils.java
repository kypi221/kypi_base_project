package com.kypi.demoproject.utils;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

/**
 * Created by Khoa on 7/20/2017.
 */

public class ColorUtils {

    public static final void setTextColor(TextView textView, int colorResourceId){
        setTextColor(textView.getContext(), textView, colorResourceId);
    }

    public static final void setTextColor(Context context, TextView textView, int colorResourceId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextColor(context.getColor(colorResourceId));
        }
        else {
            textView.setTextColor(context.getResources().getColor(colorResourceId));
        }
    }



    public static final int getColorFromResource(Context context, int colorResourceId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(colorResourceId);
        }
        else {
            return context.getResources().getColor(colorResourceId);
        }
    }
}
