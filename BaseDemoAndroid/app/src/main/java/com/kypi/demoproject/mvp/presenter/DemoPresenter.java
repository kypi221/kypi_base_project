package com.kypi.demoproject.mvp.presenter;

import com.kypi.demoproject.base.BasePresenter;
import com.kypi.demoproject.domain.entities.DemoObject;
import com.kypi.demoproject.domain.usecase.DemoUseCase;
import com.kypi.demoproject.mvp.contracts.DemoContract;

import javax.inject.Inject;

public class DemoPresenter extends BasePresenter<DemoContract.View> implements DemoContract.Presenter {

    private final DemoUseCase demoUseCase;

    @Inject
    public DemoPresenter(DemoUseCase demoUseCase){
        this.demoUseCase = demoUseCase;
    }

    @Override
    public void loadDemo() {
        DemoObject demoObject = demoUseCase.getDemoObject();
        getMvpView().showDemoObject(demoObject);
    }
}
