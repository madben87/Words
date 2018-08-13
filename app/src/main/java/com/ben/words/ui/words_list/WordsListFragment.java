package com.ben.words.ui.words_list;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ben.words.R;
import com.ben.words.core.App;
import com.ben.words.data.model.Word;
import com.ben.words.ui.words_list.adapter.WordsListAdapter;
import com.ben.words.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WordsListFragment extends Fragment implements WordsListView {

    @BindView(R.id.wordsList)
    RecyclerView wordsListView;

    @Inject
    WordListPresenter presenter;

    private Unbinder unbinder;
    private WordsListAdapter listAdapter;

    public WordsListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_words_list, container, false);

        unbinder = ButterKnife.bind(this, view);

        App.getScreenInjector().inject(this);

        presenter.attachPresenter(this);

        listAdapter = new WordsListAdapter();

        wordsListView.setHasFixedSize(true);
        wordsListView.setLayoutManager(new LinearLayoutManager(getContext()));
        wordsListView.setAdapter(listAdapter);

        EventBus.getDefault().register(this);

        presenter.updateList();

        return view;
    }

    @Override
    public void updateList(List<Word> list) {
        listAdapter.addList(list);
    }

    @Override
    public void moveToScreenWithoutBack(Class<? extends Activity> cls) {

    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusListener(MessageEvent event) {
        switch (event.msg) {
            case MessageEvent.ADD_NEW_ITEM:
                showMessage("Word is added");
                listAdapter.notifyDataSetChanged();
                break;
            case MessageEvent.ADD_NEW_ITEM_IS_ERROR:
                showMessage("ERROR: Word is not added");
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachPresenter();
        EventBus.getDefault().unregister(this);
    }
}
