package com.kypi.demoproject.base;

import com.kypi.demoproject.domain.debugs.AppLogProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.BasePresenter<T> {

    private T mMvpView;

    // Bookmark the subscription  for cancel event move next screen if activity destroyed
    protected final CompositeDisposable compositeDisposable;

    protected final AppLogProvider ILog;

    public BasePresenter(AppLogProvider logProvider) {
        this.ILog = logProvider;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
        compositeDisposable.dispose();
    }

    @Override
    public T getMvpView() {
        return mMvpView;
    }

    protected void addToDisposable(DisposableObserver observer){
        compositeDisposable.add(observer);
    }
}
