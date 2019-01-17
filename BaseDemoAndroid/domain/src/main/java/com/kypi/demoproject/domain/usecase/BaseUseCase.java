package com.kypi.demoproject.domain.usecase;

import com.kypi.demoproject.domain.authen.AuthenProvider;
import com.kypi.demoproject.domain.scheduler.SchedulerProvider;

import io.reactivex.Observable;

public class BaseUseCase {

    protected final SchedulerProvider schedulerProvider;
    protected final AuthenProvider authenProvider;

    public BaseUseCase(SchedulerProvider schedulerProvider,
                       AuthenProvider authenProvider) {
        this.schedulerProvider = schedulerProvider;
        this.authenProvider = authenProvider;
    }

    protected <T> Observable<T> doOnBackground(Observable<T> observable) {
        return observable
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui());

    }

    protected String getAuthen() {
        return authenProvider.getAuthen();
    }
}
