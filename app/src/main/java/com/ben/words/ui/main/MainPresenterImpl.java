package com.ben.words.ui.main;

import com.ben.words.ui.add_new.AddWordActivity;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter<MainView> {

    private MainView view;

    @Inject
    public MainPresenterImpl() {}

    @Override
    public void addNewWord() {
        view.moveToScreenWithoutBack(AddWordActivity.class);
    }

    @Override
    public void attachPresenter(MainView mainView) {
        this.view = mainView;
    }

    @Override
    public void detachPresenter() {
        view = null;
    }
}
