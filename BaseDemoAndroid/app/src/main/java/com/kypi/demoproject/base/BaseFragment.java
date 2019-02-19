package com.kypi.demoproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kypi.demoproject.MyApplication;
import com.kypi.demoproject.R;
import com.kypi.demoproject.app.helper.ViewClickedHelper;
import com.kypi.demoproject.di.component.DaggerFragmentComponent;
import com.kypi.demoproject.di.component.FragmentComponent;
import com.kypi.demoproject.di.module.PresenterModule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by KhoaHM on 05/15/2017.
 * To be extended by all fragments
 */
public abstract class BaseFragment extends Fragment implements BaseContract.View, View.OnClickListener {



    @Nullable
    @BindView(R.id.toolbar_title)
    public TextView tvScreenTitle;

    @Nullable
    @BindView(R.id.img_back)
    public ImageView imgBack;

    private Unbinder mUnbinder;

    private FragmentComponent fragmentComponent;

    @BindView(R.id.layout_loading)
    ViewGroup layoutLoading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Setup Fragment Component
        fragmentComponent = DaggerFragmentComponent.builder()
                .presenterModule(new PresenterModule())
                .appComponent(MyApplication.getComponent())
                .build();

        // Setup Activity Component
        setupFragmentComponent(fragmentComponent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.layout_base_fragment, container, false);
        FrameLayout frameLayout = contentView.findViewById(R.id.layout_content);
        View view = inflater.inflate(getLayoutId(), container, false);
        frameLayout.addView(view);
        mUnbinder = ButterKnife.bind(this, contentView);
        hideLoading();
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewCreated(savedInstanceState);
        if (!isRealTimeScreen()) {
            checkInternet();
        }
        GATrack();
    }


    /**
     * Setup activity component/inject
     */
    protected abstract void setupFragmentComponent(FragmentComponent fragmentComponent);

    public abstract int getLayoutId();

    public abstract void onViewCreated(@Nullable Bundle savedInstanceState);


    public void GATrack() {
        // Override If Need
    }

    protected void checkInternet() {
    }

    protected boolean isRealTimeScreen() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRealTimeScreen()) {
            checkInternet();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    /**
     * Override this if you wanna use the custom progress dialog
     */
    @Override
    public void showLoading() {
        if (getActivity() == null) {
            return;
        }

        if(layoutLoading.getVisibility() != View.VISIBLE){
            layoutLoading.setVisibility(View.VISIBLE);
        }

//        ((BaseActivity) getActivity()).showLoading();
    }

    /**
     * Override this if you wanna use the custom progress dialog
     */
    @Override
    public void hideLoading() {
        if (getActivity() == null) {
            return;
        }

        if(layoutLoading.getVisibility() != View.GONE){
            layoutLoading.setVisibility(View.GONE);
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


    public void onFragmentReload(){

    }
}

