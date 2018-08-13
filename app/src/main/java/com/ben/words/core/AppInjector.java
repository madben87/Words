package com.ben.words.core;

import com.ben.words.modules.ContextModule;
import com.ben.words.modules.RepositoryModule;
import com.ben.words.ui.add_new.AddWordPresenterImpl;
import com.ben.words.ui.main.MainActivity;
import com.ben.words.ui.words_list.WordListPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, RepositoryModule.class})
public interface AppInjector {

    void inject(AddWordPresenterImpl presenter);
    void inject(WordListPresenterImpl presenter);
}
