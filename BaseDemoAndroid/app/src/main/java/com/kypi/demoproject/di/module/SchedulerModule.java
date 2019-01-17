package com.kypi.demoproject.di.module;

import com.kypi.demoproject.app.provider.BasicAuthenProvider;
import com.kypi.demoproject.app.rx.AppSchedulerProvider;
import com.kypi.demoproject.domain.authen.AuthenProvider;
import com.kypi.demoproject.domain.scheduler.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Khoa on 8/8/2017.
 */
@Module
public class SchedulerModule {

    @Provides
    @Singleton
    public SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    public AuthenProvider provideAuthenProvider(BasicAuthenProvider provider) {
        return provider;
    }
}
