package com.ben.words.core;

import com.ben.words.modules.ContextModule;
import com.ben.words.modules.RepositoryModule;
import com.ben.words.ui.add_new_verb.AddVerbPresenterImpl;
import com.ben.words.ui.add_new_word.AddWordPresenterImpl;
import com.ben.words.ui.irr_verb_list.IrrVerbListPresenterImpl;
import com.ben.words.ui.words_list.WordListPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, RepositoryModule.class})
public interface AppInjector {

    void inject(AddWordPresenterImpl presenter);
    void inject(AddVerbPresenterImpl presenter);
    void inject(WordListPresenterImpl presenter);
    void inject(IrrVerbListPresenterImpl presenter);
}
