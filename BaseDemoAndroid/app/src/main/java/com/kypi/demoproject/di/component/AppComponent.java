package com.kypi.demoproject.di.component;

import com.kypi.demoproject.di.module.AppModule;
import com.kypi.demoproject.di.module.RepositoryModule;
import com.kypi.demoproject.domain.repository.DemoRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                RepositoryModule.class,
        })
public interface AppComponent {
        DemoRepository reDemoRepository();
}
