package com.ben.words.core;

import com.ben.words.modules.PresenterModule;
import com.ben.words.ui.add_new_verb.AddVerbActivity;
import com.ben.words.ui.add_new_word.AddWordActivity;
import com.ben.words.ui.irr_verb_list.IrrVerbListFragment;
import com.ben.words.ui.main.MainActivity;
import com.ben.words.ui.words_list.WordsListFragment;

import dagger.Component;

@ScreenScope
@Component(modules = {PresenterModule.class})
public interface ScreenInjector {

    void inject(MainActivity activity);
    void inject(AddWordActivity activity);
    void inject(AddVerbActivity activity);
    void inject(WordsListFragment activity);
    void inject(IrrVerbListFragment activity);
}
