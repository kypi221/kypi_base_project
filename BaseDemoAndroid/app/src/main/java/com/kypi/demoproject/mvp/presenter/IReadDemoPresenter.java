package com.kypi.demoproject.mvp.presenter;

import com.kypi.demoproject.app.rx.SimpleObserver;
import com.kypi.demoproject.base.BasePresenter;
import com.kypi.demoproject.domain.entities.IReadBookInfo;
import com.kypi.demoproject.domain.entities.DemoObject;
import com.kypi.demoproject.domain.usecase.IReadDemoUseCase;
import com.kypi.demoproject.mvp.contracts.IReadDemoContract;

import java.util.List;

import javax.inject.Inject;

public class IReadDemoPresenter extends BasePresenter<IReadDemoContract.View> implements IReadDemoContract.Presenter {

    private final IReadDemoUseCase demoUseCase;

    @Inject
    public IReadDemoPresenter(IReadDemoUseCase demoUseCase){
        this.demoUseCase = demoUseCase;
    }

    @Override
    public void loadDemo() {
        DemoObject demoObject = demoUseCase.getDemoObject();
        getMvpView().showDemoObject(demoObject);
    }

    @Override
    public void loadBookRanking() {
        demoUseCase.loadBookRanking().subscribeWith(new SimpleObserver<List<IReadBookInfo>>() {
            @Override
            public void onResponse(List<IReadBookInfo> bookInfos) throws Exception {
                getMvpView().showBookRanking(bookInfos);
            }

            @Override
            public void onResponseError(Throwable throwable) throws Exception {
                getMvpView().showBookRanking(null);
            }
        });
    }
}
