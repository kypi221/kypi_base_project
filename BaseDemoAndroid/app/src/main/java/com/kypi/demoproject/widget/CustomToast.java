package com.kypi.demoproject.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kypi.demoproject.R;

public class CustomToast {

    public enum ToastType{
        INFO,
        SUCCESS,
        ERROR,
        WARNING
    }


    private CustomToast() {
    }

    public static void showNotAvailableFeature(Context context){
        showInfoMgs(context, "Tính năng này đang được phát triển !");
    }

    public static void showRequiteLogin(Context context){
        showInfoMgs(context, "Hãy đăng nhập để sử dụng tính năng này nhé !");
    }

    public static void showWarningMgs(Context ctx, String msg) {
        showToast(ctx, msg, R.drawable.icon_popup_alert_warning);
    }

    public static void showErrorMgs(Context ctx, String msg) {
        showToast(ctx, msg, R.drawable.icon_popup_alert_error);
    }

    public static void showInfoMgs(Context ctx, String msg) {
        showToast(ctx, msg, R.drawable.icon_popup_alert_info);
    }

    public static void showSuccessMgs(Context ctx, String msg) {
        showToast(ctx, msg, R.drawable.icon_popup_alert_success);
    }

    private static void showToast(Context ctx, String msg, int iconResId){
        if(ctx == null){
            return;
        }
        Context context = ctx.getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customToastroot = inflater.inflate(R.layout.layout_custom_toast, null);
        TextView mytextview = (TextView) customToastroot.findViewById(R.id.tv_content);
        ImageView imgIcon = (ImageView) customToastroot.findViewById(R.id.img_icon_toast);

        mytextview.setText(msg);
        imgIcon.setImageResource(iconResId);


        Toast customtoast = new Toast(context);
        customtoast.setView(customToastroot);
        customtoast.setGravity(17, 0, 0);
        customtoast.setDuration(Toast.LENGTH_LONG);
        customtoast.show();
    }
}
