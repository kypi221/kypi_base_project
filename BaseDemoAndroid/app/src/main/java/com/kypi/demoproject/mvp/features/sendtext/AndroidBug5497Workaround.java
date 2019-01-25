package com.kypi.demoproject.mvp.features.sendtext;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.kypi.demoproject.R;

public class AndroidBug5497Workaround {
    // For more information, see https://issuetracker.google.com/issues/36911528
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    public static void assistActivity (Activity activity) {
        new AndroidBug5497Workaround(activity);
    }
    public static void assistActivity (Dialog dialog) {
        new AndroidBug5497Workaround(dialog);
    }

    private View mChildOfContent;
    private int usableHeightPrevious;
    private ValueAnimator animateCollapseView = null;
    private FrameLayout.LayoutParams frameLayoutParams;
    private Dialog dialog;
    private int count = 0;

    private AndroidBug5497Workaround(Activity activity) {
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent(this);
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }


    private AndroidBug5497Workaround(Dialog dialog) {
        count = 0;
        this.dialog = dialog;
        FrameLayout content = (FrameLayout) dialog.findViewById(R.id.root_container);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent(this);

                Log.d("KhoaHM", "onGlobalLayout ");
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    private void possiblyResizeChildOfContent(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;

            Log.d("KhoaHM", "heightDifference " + heightDifference);
            if (heightDifference > (usableHeightSansKeyboard/4)) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;

                // we need to create the collapse animator the only the first time we rise the keyboard
                if (this.animateCollapseView == null) {
                    this.animateCollapseView = ValueAnimator.ofFloat(0f, 255f);
                    this.animateCollapseView.setDuration(500);
                    this.animateCollapseView.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Float value = (Float) animation.getAnimatedValue();
                            mChildOfContent.setAlpha(value.floatValue() / 255f);
                            mChildOfContent.requestLayout();
//                            Log.d("KhoaHM", "value.intValue() " + value.intValue());
                        }
                    });
                }

                this.animateCollapseView.start();
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
                if(dialog != null && count >=1){
                    dialog.dismiss();
                }
                count += 1;
            }

            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }
}


