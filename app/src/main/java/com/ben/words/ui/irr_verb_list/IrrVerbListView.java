package com.ben.words.ui.irr_verb_list;

import com.ben.words.core.MVPView;
import com.ben.words.data.model.IrregularVerb;

import java.util.List;

public interface IrrVerbListView extends MVPView {

    void updateList(List<IrregularVerb> list);
}
