package com.ben.words.core;

import com.ben.words.modules.ContextModule;
import com.ben.words.modules.RepositoryModule;
import com.ben.words.ui.add_new.AddWordPresenterImpl;
import com.ben.words.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, RepositoryModule.class})
public interface AppInjector {

    //void inject(MainActivity activity);
    void inject(AddWordPresenterImpl presenter);
    //void inject(MainActivity activity);
    //void inject(MainActivity activity);
}
