package com.ben.words.core;

import com.ben.words.modules.PresenterModule;
import com.ben.words.ui.add_new.AddWordActivity;
import com.ben.words.ui.main.MainActivity;

import dagger.Component;

@ScreenScope
@Component(modules = {PresenterModule.class})
public interface ScreenInjector {

    void inject(MainActivity activity);
    void inject(AddWordActivity activity);
}
