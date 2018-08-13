package com.ben.words.ui.words_list;

import com.ben.words.core.MVPView;
import com.ben.words.data.model.Word;

import java.util.List;

public interface WordsListView extends MVPView {

    void updateList(List<Word> list);
}
