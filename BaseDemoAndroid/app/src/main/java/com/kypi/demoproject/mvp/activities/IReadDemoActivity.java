package com.kypi.demoproject.mvp.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kypi.demoproject.R;
import com.kypi.demoproject.base.BaseActivity;
import com.kypi.demoproject.mvp.adapters.IReadBookInfoSimpleAdapter;
import com.kypi.demoproject.utils.ImageSavedUtil;
import com.kypi.demoproject.base.MyCustomToast;
import com.kypi.demoproject.di.component.ActivityComponent;
import com.kypi.demoproject.domain.entities.IReadBookInfo;
import com.kypi.demoproject.domain.entities.DemoObject;
import com.kypi.demoproject.mvp.contracts.IReadDemoContract;
import com.kypi.demoproject.mvp.presenter.IReadDemoPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class IReadDemoActivity extends BaseActivity implements IReadDemoContract.View {


    private static final int REQUEST_CODE_SAVE = 44;
    private static final int REQUEST_CODE_CAMERA = 55;
    private static final int REQUEST_CODE_GALLERY = 66;
    private static final int REQUEST_CODE_TAKE_PHOTO = 77;
    private static final int REQUEST_CODE_CHOOSE_PHOTO = 88;
    private static final String[] CAMERA_PERMISSION = {Manifest.permission.CAMERA};
    private static final String[] GALLERY_PERMISSION = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final String[] SAVE_PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Inject
    IReadDemoPresenter demoPresenter;

    @BindView(R.id.tv_demo_name)
    TextView tvDemoName;

    @BindView(R.id.layout_root)
    ViewGroup layoutRoot;

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
        return R.layout.activity_iread_demo;
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

        showMessage("Danh sách Book : " + bookInfos.size(), MyCustomToast.ToastType.SUCCESS);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new IReadBookInfoSimpleAdapter(bookInfos, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @OnClick(R.id.btn_save_image)
    public void saveImage(){
        ImageSavedUtil.enterSaveMemeFlow(layoutRoot, this, SAVE_PERMISSION, REQUEST_CODE_SAVE);
    }


}
