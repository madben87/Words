package com.ben.words.modules;

import com.ben.words.core.ScreenScope;
import com.ben.words.ui.main.MainPresenter;
import com.ben.words.ui.main.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @ScreenScope
    MainPresenter providesPresenter() {
        return new MainPresenterImpl();
    }
}
