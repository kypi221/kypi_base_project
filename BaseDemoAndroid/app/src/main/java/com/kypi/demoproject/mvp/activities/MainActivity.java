package com.kypi.demoproject.mvp.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.kypi.demoproject.R;
import com.kypi.demoproject.base.BaseActivity;
import com.kypi.demoproject.di.component.ActivityComponent;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_demo_name)
    TextView tvDemoName;

    /**
     * get layoutEyeProtection to inflate
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * Setup activity component/inject
     *
     * @param mActivityComponent
     */
    @Override
    protected void setupActivityComponent(ActivityComponent mActivityComponent) {
        mActivityComponent.inject(this);
    }

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        IReadDemoActivity.showMe(this);
        finish();
    }
}
