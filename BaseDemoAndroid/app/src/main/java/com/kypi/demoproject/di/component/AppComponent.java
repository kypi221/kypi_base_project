package com.kypi.demoproject.di.component;

import com.kypi.demoproject.di.module.AppModule;
import com.kypi.demoproject.di.module.RepositoryModule;
import com.kypi.demoproject.di.module.SchedulerModule;
import com.kypi.demoproject.domain.repository.DemoRepository;
import com.kypi.demoproject.domain.scheduler.SchedulerProvider;
import com.kypi.demoproject.domain.usecase.DemoUseCase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                RepositoryModule.class,
                SchedulerModule.class,
        })
public interface AppComponent {
    SchedulerProvider schedulerProvider();


    DemoUseCase demoUseCase();
}
