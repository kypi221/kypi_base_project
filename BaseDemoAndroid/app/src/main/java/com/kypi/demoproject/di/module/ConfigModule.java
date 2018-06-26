package com.kypi.demoproject.di.module;

import com.kypi.demoproject.app.debug.DebugConfigImpl;
import com.kypi.demoproject.app.debug.DomainLogImpl;
import com.kypi.demoproject.domain.debugs.DebugConfig;
import com.kypi.demoproject.domain.debugs.DomainLog;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfigModule {

    @Provides
    @Singleton
    public DebugConfig provideDebugConfig() {
        return new DebugConfigImpl();
    }



    @Provides
    @Singleton
    public DomainLog provideDomainLog() {
        return new DomainLogImpl();
    }
}
