package com.kypi.demoproject.mvp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.kypi.demoproject.R;
import com.kypi.demoproject.base.BaseActivity;
import com.kypi.demoproject.di.component.ActivityComponent;
import com.kypi.demoproject.domain.entities.DemoObject;
import com.kypi.demoproject.domain.repository.DemoRepository;
import com.kypi.demoproject.mvp.contracts.DemoContract;
import com.kypi.demoproject.mvp.presenter.DemoPresenter;

import javax.inject.Inject;

import butterknife.BindView;

public class DemoActivity extends BaseActivity implements DemoContract.View {

    @Inject
    DemoPresenter demoPresenter;

    @BindView(R.id.tv_demo_name)
    TextView tvDemoName;


    public static void showMe(BaseActivity activity) {
        Intent intent = new Intent(activity, DemoActivity.class);
        activity.startActivity(intent);
    }

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
        demoPresenter.attachView(this);
        demoPresenter.loadDemo();
    }

    @Override
    public void showDemoObject(DemoObject demoObject) {
        tvDemoName.setText(demoObject.name);
    }

}
