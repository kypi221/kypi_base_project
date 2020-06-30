package com.kypi.demoproject.mvp.features.sendtext;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kypi.demoproject.R;
import com.kypi.demoproject.base.BaseActivity;
import com.kypi.demoproject.base.BaseDialog;
import com.kypi.demoproject.di.component.DialogComponent;

import butterknife.BindView;
import butterknife.OnClick;

public class SendTextDialog extends BaseDialog {


    @BindView(R.id.tv_send)
    TextView tvSend;

    @BindView(R.id.tv_note)
    TextView tvNote;

    @BindView(R.id.edit_comment)
    EditText editText;


    @BindView(R.id.layout_background_top)
    ViewGroup viewGroup;


    private SendTextDialogCallBack callback;

    public static SendTextDialog showMe(BaseActivity baseActivity, String note, String send, String editHint, SendTextDialogCallBack callback) {
        final SendTextDialog mBottomSheetDialog = showMe(baseActivity, callback);
        mBottomSheetDialog.tvSend.setText(send);
        mBottomSheetDialog.tvNote.setText(note);
        mBottomSheetDialog.editText.setHint(editHint);
        return mBottomSheetDialog;
    }


    public static SendTextDialog showMe(BaseActivity baseActivity, SendTextDialogCallBack callback) {
        final SendTextDialog mBottomSheetDialog = new SendTextDialog(baseActivity, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mBottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mBottomSheetDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mBottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.setBaseActivity(baseActivity);
        mBottomSheetDialog.setCallBack(callback);

        mBottomSheetDialog.show();

        return mBottomSheetDialog;
    }

    private void setCallBack(SendTextDialogCallBack callback) {
        this.callback = callback;
    }


    public SendTextDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void setupFragmentComponent(DialogComponent fragmentComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.popup_send_text;
    }

    @Override
    public void onViewCreated() {
        AndroidBug5497Workaround.assistActivity(this);
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString();
                callback.onTextSubmit(string, SendTextDialog.this);
                dismiss();
            }
        });

    }

    @OnClick(R.id.layout_background_top)
    public void close(){
        dismiss();
    }
}
