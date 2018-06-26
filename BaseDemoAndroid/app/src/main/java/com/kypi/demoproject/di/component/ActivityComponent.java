package com.kypi.demoproject.di.component;

import com.kypi.demoproject.mvp.activities.IReadDemoActivity;
import com.kypi.demoproject.mvp.activities.MainActivity;
import com.kypi.demoproject.di.module.ActivityModule;
import com.kypi.demoproject.di.module.PresenterModule;
import com.kypi.demoproject.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {ActivityModule.class, PresenterModule.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(IReadDemoActivity demoActivity);
}
