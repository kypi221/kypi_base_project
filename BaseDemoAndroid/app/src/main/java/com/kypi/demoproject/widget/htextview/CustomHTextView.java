package com.kypi.demoproject.widget.htextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.hanks.htextview.animatetext.IHText;

/**
 * Created by KhoaHM on 5/16/2017.
 */

public class CustomHTextView extends HTextView {

    private IHText mIHText = new CustomLineElement();
    private AttributeSet attrs;
    private int defStyle;

    public CustomHTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomHTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomHTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        this.attrs = attrs;
        this.defStyle = defStyle;

        // Get the attributes array
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, com.hanks.htextview.R
                .styleable.HTextView);
        final int animateType = typedArray.getInt(com.hanks.htextview.R.styleable
                .HTextView_animateType, 0);
        final String fontAsset = typedArray.getString(com.hanks.htextview.R.styleable
                .HTextView_fontAsset);

        // Set custom typeface
        if (fontAsset != null && !fontAsset.trim().isEmpty()) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), fontAsset));
        }

        mIHText = new CustomLineElement();
        typedArray.recycle();
        initHText(attrs, defStyle);
    }

    private void initHText(AttributeSet attrs, int defStyle) {
        mIHText.init(this, attrs, defStyle);
    }


    public void animateText(CharSequence text) {
        mIHText.animateText(text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mIHText.onDraw(canvas);
    }

    public void reset(CharSequence text) {
        mIHText.reset(text);
    }

    public void setAnimateType(HTextViewType type) {
        mIHText = new CustomLineElement();
        initHText(attrs, defStyle);
    }

    public void setDuration(long duration) {
        ((CustomLineElement) mIHText).setDuration(duration);
    }
}
