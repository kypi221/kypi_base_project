package com.kypi.demoproject.domain.usecase;

import com.kypi.demoproject.domain.scheduler.SchedulerProvider;

public class DemoUseCase extends BaseUseCase {

    public DemoUseCase(SchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }
}
