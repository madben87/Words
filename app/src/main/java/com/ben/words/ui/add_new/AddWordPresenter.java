package com.ben.words.ui.add_new;

import com.ben.words.core.Presenter;
import com.ben.words.data.model.Word;

public interface AddWordPresenter<V extends AddWordView> extends Presenter<V> {

    void addNewItem(Word word);
}
