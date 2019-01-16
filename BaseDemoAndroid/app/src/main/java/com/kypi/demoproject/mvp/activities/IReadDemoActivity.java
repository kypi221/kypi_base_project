package com.kypi.demoproject.mvp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kypi.demoproject.R;
import com.kypi.demoproject.base.BaseActivity;
import com.kypi.demoproject.mvp.adapters.IReadBookInfoSimpleAdapter;
import com.kypi.demoproject.widget.CustomToast;
import com.kypi.demoproject.di.component.ActivityComponent;
import com.kypi.demoproject.domain.entities.IReadBookInfo;
import com.kypi.demoproject.domain.entities.DemoObject;
import com.kypi.demoproject.mvp.contracts.IReadDemoContract;
import com.kypi.demoproject.mvp.presenter.IReadDemoPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class IReadDemoActivity extends BaseActivity implements IReadDemoContract.View {

    @Inject
    IReadDemoPresenter demoPresenter;

    @BindView(R.id.tv_demo_name)
    TextView tvDemoName;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private IReadBookInfoSimpleAdapter adapter;

    public static void showMe(BaseActivity activity) {
        Intent intent = new Intent(activity, IReadDemoActivity.class);
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
        demoPresenter.loadBookRanking();
    }

    @Override
    public void showDemoObject(DemoObject demoObject) {
        tvDemoName.setText(demoObject.name);
    }

    @Override
    public void showBookRanking(List<IReadBookInfo> bookInfos) {
        if (bookInfos == null){
            return;
        }


        showMessage("Danh sách Book : " + bookInfos.size(), CustomToast.ToastType.SUCCESS);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new IReadBookInfoSimpleAdapter(bookInfos, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

}
