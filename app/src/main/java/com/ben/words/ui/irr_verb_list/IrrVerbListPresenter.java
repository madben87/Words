package com.ben.words.ui.irr_verb_list;

import com.ben.words.core.Presenter;

public interface IrrVerbListPresenter<V extends IrrVerbListView> extends Presenter<V> {

    void updateList();
}
