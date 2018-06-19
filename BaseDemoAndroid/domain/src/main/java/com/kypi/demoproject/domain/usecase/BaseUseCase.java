package com.kypi.demoproject.domain.usecase;

import com.kypi.demoproject.domain.scheduler.SchedulerProvider;

import io.reactivex.Observable;

public class BaseUseCase {

    protected final SchedulerProvider schedulerProvider;

    public BaseUseCase(SchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
    }

    protected <T> Observable<T> doOnBackground(Observable<T> observable){
        return observable
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui());

    }
}
