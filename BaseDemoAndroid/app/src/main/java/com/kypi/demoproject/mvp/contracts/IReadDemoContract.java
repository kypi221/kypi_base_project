package com.kypi.demoproject.mvp.contracts;

import com.kypi.demoproject.domain.entities.IReadBookInfo;
import com.kypi.demoproject.domain.entities.DemoObject;

import java.util.List;

public class IReadDemoContract {

    public interface View extends BaseContract.View {
        void showDemoObject(DemoObject demoObject);

        void showBookRanking(List<IReadBookInfo> bookInfos);
    }

    public interface Presenter extends BaseContract.BasePresenter<IReadDemoContract.View> {
        void loadDemo();

        void loadBookRanking();
    }
}
