package com.kypi.demoproject.base;

import com.kypi.demoproject.mvp.contracts.BaseContract;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.BasePresenter<T> {

    private T mMvpView;

    // Bookmark the subscription  for cancel event move next screen if activity destroyed
    protected final CompositeDisposable compositeDisposable;

    public BasePresenter() {
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public T getMvpView() {
        return mMvpView;
    }
}
