package com.ben.words.core;

import android.app.Application;

import com.ben.words.modules.ContextModule;
import com.ben.words.modules.PresenterModule;

public class App extends Application {

    private static App appInstance;
    private AppInjector appInjector;
    private ScreenInjector screenInjector;

    public static App getAppInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appInstance = this;

        appInjector = DaggerAppInjector
                .builder()
                .contextModule(new ContextModule(this))
                .build();

        screenInjector = DaggerScreenInjector
                .builder()
                .presenterModule(new PresenterModule())
                .build();
    }

    public static AppInjector getAppInjector() {
        return getAppInstance().appInjector;
    }

    public static ScreenInjector getScreenInjector() {
        return getAppInstance().screenInjector;
    }
}
