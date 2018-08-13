package com.ben.words.modules;

import com.ben.words.core.ScreenScope;
import com.ben.words.ui.add_new.AddWordPresenter;
import com.ben.words.ui.add_new.AddWordPresenterImpl;
import com.ben.words.ui.main.MainPresenter;
import com.ben.words.ui.main.MainPresenterImpl;
import com.ben.words.ui.words_list.WordListPresenter;
import com.ben.words.ui.words_list.WordListPresenterImpl;
import com.ben.words.ui.words_list.WordsListView;

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
    WordListPresenter providesWordListPresenter() {
        return new WordListPresenterImpl();
    }
}
