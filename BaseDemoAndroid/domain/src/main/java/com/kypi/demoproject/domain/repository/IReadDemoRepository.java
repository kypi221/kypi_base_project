package com.kypi.demoproject.domain.repository;

import com.kypi.demoproject.domain.entities.IReadBookInfo;
import com.kypi.demoproject.domain.entities.DemoObject;

import java.util.List;

import io.reactivex.Observable;

public interface IReadDemoRepository {
    public DemoObject getDemoObject();

    public Observable<List<IReadBookInfo>> loadBookRanking();
}
