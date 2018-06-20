package com.kypi.demoproject.mvp.contracts;

public interface BaseContract {

    public interface BasePresenter<ViewType extends BaseContract.View> {
        void attachView(ViewType mvpView);
        void detachView();
        ViewType getMvpView();
    }

    public interface View {
        void showLoading();
        void hideLoading();

        void showMessage(String msg, int type);
    }
}
