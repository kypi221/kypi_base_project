package com.kypi.demoproject.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.kypi.demoproject.MyApplication;
import com.kypi.demoproject.R;
import com.kypi.demoproject.app.helper.ViewClickedHelper;
import com.kypi.demoproject.di.component.DaggerDialogComponent;
import com.kypi.demoproject.di.component.DialogComponent;
import com.kypi.demoproject.di.module.PresenterModule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialog extends Dialog implements BaseContract.View, View.OnClickListener {


    @Nullable
    @BindView(R.id.toolbar_title)
    public TextView tvScreenTitle;

    @Nullable
    @BindView(R.id.img_back)
    public ImageView imgBack;

    private Unbinder mUnbinder;

    private DialogComponent dialogComponent;

    protected ProgressDialog progressDialog;
    protected BaseActivity baseActivity;

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Setup Fragment Component
        dialogComponent = DaggerDialogComponent.builder()
                .presenterModule(new PresenterModule())
                .appComponent(MyApplication.getComponent())
                .build();

        // Setup Activity Component
        setupFragmentComponent(dialogComponent);

        View contentView = LayoutInflater.from(getContext()).inflate(getLayoutId(), null, false);
        mUnbinder = ButterKnife.bind(this, contentView);
        hideLoading();
        setContentView(contentView);

        onViewCreated();
    }

    /**
     * Setup activity component/inject
     */
    protected abstract void setupFragmentComponent(DialogComponent dialogComponent);

    public abstract int getLayoutId();

    public abstract void onViewCreated();

    /**
     * Override this if you wanna use the custom progress dialog
     */
    @Override
    public void showLoading() {
        if (getActivity() == null) {
            return;
        }

        if (progressDialog != null && progressDialog.isShowing()) {
            return;
        }
        progressDialog = showLoadingDialog(getActivity());
    }

    /**
     * Override this if you wanna use the custom progress dialog
     */
    @Override
    public void hideLoading() {
        if (getActivity() == null) {
            return;
        }
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }


    /**
     * Handle Error
     */
    @Override
    public void showMessage(String msg, MyCustomToast.ToastType toastType) {

        switch (toastType) {
            case ERROR:
                MyCustomToast.showErrorMgs(getActivity(), msg);
                break;
            case SUCCESS:
                MyCustomToast.showSuccessMgs(getActivity(), msg);
                break;
            case WARNING:
                MyCustomToast.showWarningMgs(getActivity(), msg);
                break;
            case INFO:
                MyCustomToast.showInfoMgs(getActivity(), msg);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        ViewClickedHelper.handleViewClicked((BaseActivity) getActivity(), v);
    }

    private Context getActivity(){
        return getContext();
    }


    public void setBaseActivity(BaseActivity baseActivity){
        this.baseActivity = baseActivity;
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


    public interface DialogCallback<T>{
        void onSuccess(T object);
    }
}
