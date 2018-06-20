package com.kypi.demoproject.mvp.contracts;

import com.kypi.demoproject.domain.entities.DemoObject;

public class DemoContract {

    public interface View extends BaseContract.View {
        void showDemoObject(DemoObject demoObject);
    }

    public interface Presenter extends BaseContract.BasePresenter<DemoContract.View> {
        void loadDemo();
    }
}
