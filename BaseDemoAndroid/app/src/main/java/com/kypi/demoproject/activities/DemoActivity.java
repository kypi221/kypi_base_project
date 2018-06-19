package com.kypi.demoproject.activities;

import android.os.Bundle;

import com.kypi.demoproject.base.BaseActivity;
import com.kypi.demoproject.di.component.ActivityComponent;

public class DemoActivity extends BaseActivity {
    /**
     * get layoutEyeProtection to inflate
     */
    @Override
    protected int getLayoutId() {
        return 0;
    }

    /**
     * Setup activity component/inject
     *
     * @param mActivityComponent
     */
    @Override
    protected void setupActivityComponent(ActivityComponent mActivityComponent) {

    }

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {

    }
}
