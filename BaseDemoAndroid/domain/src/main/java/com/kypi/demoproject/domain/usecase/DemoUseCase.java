package com.kypi.demoproject.domain.usecase;

import com.kypi.demoproject.domain.entities.DemoObject;
import com.kypi.demoproject.domain.repository.DemoRepository;
import com.kypi.demoproject.domain.scheduler.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DemoUseCase extends BaseUseCase {

    private final DemoRepository repository;

    @Inject
    public DemoUseCase(SchedulerProvider schedulerProvider, DemoRepository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    public DemoObject getDemoObject() {
        return repository.getDemoObject();
    }
}
