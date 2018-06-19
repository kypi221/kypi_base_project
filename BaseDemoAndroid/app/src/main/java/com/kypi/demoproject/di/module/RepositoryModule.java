package com.kypi.demoproject.di.module;

import com.kypi.demoproject.data.repository.DemoRepositoryImpl;
import com.kypi.demoproject.domain.repository.DemoRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public DemoRepository provideDemoRepository(DemoRepositoryImpl demoRepository) {
        return demoRepository;
    }

}
