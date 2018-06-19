package com.kypi.demoproject.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.kypi.demoproject.MyApplication;
import com.kypi.demoproject.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final MyApplication mApplication;

    public AppModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }


    @Provides
    @Singleton
    Application provideIReadApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return mApplication.getSharedPreferences(mApplication.getString(R.string.app_name),
                Context.MODE_PRIVATE);
    }
}
