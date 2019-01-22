package com.kypi.demoproject.di.component;


import com.kypi.demoproject.di.module.PresenterModule;
import com.kypi.demoproject.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by KhoaHM on 6/1/2017.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = PresenterModule.class)
public interface FragmentComponent {

}
