package com.kypi.demoproject.di.module;

import com.kypi.demoproject.mvp.contracts.IReadDemoContract;
import com.kypi.demoproject.mvp.presenter.IReadDemoPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Khoa on 7/31/2017.
 */
@Module
public class PresenterModule {
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }



    @Provides
    IReadDemoContract.Presenter presenter(IReadDemoPresenter demoPresenter){
        return demoPresenter;
    }

}
