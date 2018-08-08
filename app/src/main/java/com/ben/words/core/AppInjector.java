package com.ben.words.core;

import com.ben.words.modules.ContextModule;
import com.ben.words.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class/*, DataModule.class*/})
public interface AppInjector {

    //void inject(MainActivity activity);
}
