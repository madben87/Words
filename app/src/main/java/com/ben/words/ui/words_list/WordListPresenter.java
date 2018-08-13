package com.ben.words.ui.words_list;

import com.ben.words.core.Presenter;

public interface WordListPresenter<V extends WordsListView> extends Presenter<V> {

    void updateList();
}
