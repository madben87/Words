package com.ben.words.ui.add_new_verb;

import com.ben.words.core.Presenter;
import com.ben.words.data.model.IrregularVerb;

public interface AddVerbPresenter<V extends AddVerbView> extends Presenter<V> {
    void addNewItem(IrregularVerb verb);
}
