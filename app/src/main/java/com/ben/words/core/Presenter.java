package com.ben.words.core;

public interface Presenter<V extends MVPView> {

    void attachPresenter(V v);
    void detachPresenter();
}
