package com.ben.words.ui.main;

import com.ben.words.data.Repository;
import com.ben.words.data.model.Word;
import com.ben.words.data.realm_db.RealmRepositoryImpl;
import com.ben.words.ui.add_new.AddWordActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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

    @Override
    public void test() {
        Repository repository = new RealmRepositoryImpl();

        Word word = new Word();
        word.setPartsOfSpeech("adj");
        word.setValue("test");
        word.setTranslates("тест");
        word.setTranscription("тест");

        Word word1 = new Word();
        word1.setPartsOfSpeech("noun");
        word1.setValue("demo");
        word1.setTranslates("демо");
        word1.setTranscription("дэмо");

        CompositeDisposable disposable = new CompositeDisposable();

        disposable.add((Disposable) repository.addNewItem(word)
                .debounce(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Word>() {
                    @Override
                    public void onNext(Word w) {
                        String s = w.getPartsOfSpeech();
                        String d = s;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

        view.moveToScreenWithoutBack(AddWordActivity.class);
    }
}
