package com.ben.words.modules;

import com.ben.words.core.ScreenScope;
import com.ben.words.ui.add_new_verb.AddVerbPresenter;
import com.ben.words.ui.add_new_verb.AddVerbPresenterImpl;
import com.ben.words.ui.add_new_word.AddWordPresenter;
import com.ben.words.ui.add_new_word.AddWordPresenterImpl;
import com.ben.words.ui.irr_verb_list.IrrVerbListPresenter;
import com.ben.words.ui.irr_verb_list.IrrVerbListPresenterImpl;
import com.ben.words.ui.main.MainPresenter;
import com.ben.words.ui.main.MainPresenterImpl;
import com.ben.words.ui.words_list.WordListPresenter;
import com.ben.words.ui.words_list.WordListPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @ScreenScope
    MainPresenter providesPresenter() {
        return new MainPresenterImpl();
    }

    @Provides
    @ScreenScope
    AddWordPresenter providesAddPresenter() {
        return new AddWordPresenterImpl();
    }

    @Provides
    @ScreenScope
    AddVerbPresenter providesAddVerbPresenter() {
        return new AddVerbPresenterImpl();
    }

    @Provides
    @ScreenScope
    WordListPresenter providesWordListPresenter() {
        return new WordListPresenterImpl();
    }

    @Provides
    @ScreenScope
    IrrVerbListPresenter providesIrrVerbListPresenter() {
        return new IrrVerbListPresenterImpl();
    }
}
