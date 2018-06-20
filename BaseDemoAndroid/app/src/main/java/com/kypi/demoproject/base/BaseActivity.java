package com.kypi.demoproject.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.kypi.demoproject.MyApplication;
import com.kypi.demoproject.R;
import com.kypi.demoproject.di.component.ActivityComponent;
import com.kypi.demoproject.di.component.DaggerActivityComponent;
import com.kypi.demoproject.di.module.ActivityModule;
import com.kypi.demoproject.di.module.PresenterModule;
import com.kypi.demoproject.mvp.contracts.BaseContract;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, BaseContract.View{

    protected ProgressDialog progressDialog;
    private ActivityComponent mActivityComponent;

    /**
     * get layoutEyeProtection to inflate
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * Setup activity component/inject
     */
    protected abstract void setupActivityComponent(ActivityComponent mActivityComponent);


    protected abstract void onActivityCreated(Bundle savedInstanceState);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Change status bar to black color
        changeStatusBarColor();
        super.onCreate(savedInstanceState);

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .presenterModule(new PresenterModule())
                .appComponent(MyApplication.getComponent())
                .build();

        // Setup Activity Component
        setupActivityComponent(mActivityComponent);

        // Set Content View
        setContentView(getLayoutId());

        //bind view here for all activities extending this Activity
        ButterKnife.bind(this);


        onActivityCreated(savedInstanceState);

    }


    /**
     * Override this if you wanna use the custom progress dialog
     */
    @Override
    public void showLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            return;
        }
        progressDialog = showLoadingDialog(this);
    }

    /**
     * Override this if you wanna use the custom progress dialog
     */
    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void showMessage(String msg, int toastType) {
        // TODO: 6/20/2018   Cần setup toast
    }


    /**
     * Đổi màu của status bar
     */
    protected void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
    }



    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }


    /**
     * Tạo ra dialog loading
     * @param context
     * @return
     */
    private static ProgressDialog showLoadingDialog(Context context) {
        if (context == null) {
            return null;
        }
        try {
            ProgressDialog progressDialog = ProgressDialog.show(context, null, null, true, false);
            if (progressDialog.getWindow() != null) {
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
            return progressDialog;
        } catch (WindowManager.BadTokenException e) {
            return new ProgressDialog(context);
        }
    }

}
