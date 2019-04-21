package com.kypi.demoproject.di.component;

import com.kypi.demoproject.di.module.AppModule;
import com.kypi.demoproject.di.module.ProviderModule;
import com.kypi.demoproject.di.module.RemoteModule;
import com.kypi.demoproject.di.module.RepositoryModule;
import com.kypi.demoproject.di.module.SchedulerModule;
import com.kypi.demoproject.domain.debugs.AppLogProvider;
import com.kypi.demoproject.domain.scheduler.SchedulerProvider;
import com.kypi.demoproject.domain.usecase.IReadDemoUseCase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                RepositoryModule.class,
                SchedulerModule.class,
                ProviderModule.class,
                RemoteModule.class,
        })
public interface AppComponent {
    AppLogProvider appLogProvider();

    SchedulerProvider schedulerProvider();
    IReadDemoUseCase demoUseCase();
}
