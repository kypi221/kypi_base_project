package com.kypi.demoproject;

import android.app.Application;

import com.kypi.demoproject.di.component.AppComponent;
import com.kypi.demoproject.di.component.DaggerAppComponent;
import com.kypi.demoproject.di.module.AppModule;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }


    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;


        // initialize component helper
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public static AppComponent getComponent() {
        return MyApplication.getInstance().mAppComponent;
    }

}
