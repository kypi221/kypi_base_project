package com.kypi.demoproject.di.module;

import com.kypi.demoproject.app.debug.DebugConfigImpl;
import com.kypi.demoproject.domain.debugs.DebugConfig;

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
}
