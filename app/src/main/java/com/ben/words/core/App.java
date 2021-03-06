package com.ben.words.core;

import android.app.Application;

import com.ben.words.modules.ContextModule;
import com.ben.words.modules.PresenterModule;
import com.ben.words.modules.RepositoryModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static App appInstance;
    private AppInjector appInjector;
    private ScreenInjector screenInjector;

    private int mainPageState;

    public static App getAppInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder().name("words.realm").build();

        Realm.setDefaultConfiguration(config);

        appInstance = this;

        appInjector = DaggerAppInjector
                .builder()
                .contextModule(new ContextModule(this))
                .repositoryModule(new RepositoryModule())
                .build();

        screenInjector = DaggerScreenInjector
                .builder()
                .presenterModule(new PresenterModule())
                .build();
    }

    public int getMainPageState() {
        return mainPageState;
    }

    public void setMainPageState(int mainPageState) {
        this.mainPageState = mainPageState;
    }

    public static AppInjector getAppInjector() {
        return getAppInstance().appInjector;
    }

    public static ScreenInjector getScreenInjector() {
        return getAppInstance().screenInjector;
    }
}
