package com.kypi.demoproject.domain.usecase;

import com.kypi.demoproject.domain.entities.IReadBookInfo;
import com.kypi.demoproject.domain.entities.DemoObject;
import com.kypi.demoproject.domain.repository.IReadDemoRepository;
import com.kypi.demoproject.domain.scheduler.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class IReadDemoUseCase extends BaseUseCase {

    private final IReadDemoRepository repository;

    @Inject
    public IReadDemoUseCase(SchedulerProvider schedulerProvider, IReadDemoRepository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    public DemoObject getDemoObject() {
        return repository.getDemoObject();
    }

    public Observable<List<IReadBookInfo>> loadBookRanking() {
        return doOnBackground(repository.loadBookRanking());
    }
}
