package com.kypi.demoproject.data.repository;

import com.kypi.demoproject.data.remote.IReadDemoApiService;
import com.kypi.demoproject.data.remote.response.IReadListBookDemoResponse;
import com.kypi.demoproject.domain.entities.IReadBookInfo;
import com.kypi.demoproject.domain.entities.DemoObject;
import com.kypi.demoproject.domain.repository.IReadDemoRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

@Singleton
public class IReadRankingBookRepositoryImpl implements IReadDemoRepository {

    private final DemoObject demoObject;
    private final IReadDemoApiService demoApiService;

    @Inject
    public IReadRankingBookRepositoryImpl(IReadDemoApiService demoApiService) {
        this.demoApiService = demoApiService;
        this.demoObject = new DemoObject("This is demo object name");
    }

    @Override
    public DemoObject getDemoObject() {
        return demoObject;
    }

    @Override
    public Observable<List<IReadBookInfo>> loadBookRanking() {
        return demoApiService.getRankingBooks("",1, 20).map(new Function<IReadListBookDemoResponse, List<IReadBookInfo>>() {
            @Override
            public List<IReadBookInfo> apply(IReadListBookDemoResponse baseResponse) throws Exception {
                return baseResponse.books;
            }
        });
    }
}
