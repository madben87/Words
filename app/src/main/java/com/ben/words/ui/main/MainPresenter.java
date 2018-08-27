package com.ben.words.ui.main;

import com.ben.words.core.Presenter;

import javax.inject.Inject;

public interface MainPresenter<V extends MainView> extends Presenter<V> {

    void addNewItem(int action);
}
