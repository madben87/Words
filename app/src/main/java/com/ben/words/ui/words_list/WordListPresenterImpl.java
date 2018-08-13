package com.ben.words.ui.words_list;

import com.ben.words.core.App;
import com.ben.words.data.Repository;
import com.ben.words.data.model.Word;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WordListPresenterImpl implements WordListPresenter<WordsListView> {

    @Inject
    Repository repository;

    private WordsListView view;
    private CompositeDisposable disposable;

    public WordListPresenterImpl() {
        App.getAppInjector().inject(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void attachPresenter(WordsListView wordsListView) {
        this.view = wordsListView;
    }

    @Override
    public void detachPresenter() {
        view = null;
        disposable.clear();
    }

    @Override
    public void updateList() {
        disposable.add((Disposable) repository.getList(Word.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Word>>() {
                    @Override
                    public void onNext(List<Word> list) {
                        if (list != null && list.size() > 0) {
                            view.updateList(list);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }
}
