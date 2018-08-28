package com.ben.words.ui.main;

import com.ben.words.data.Repository;
import com.ben.words.data.realm_db.RealmWordRepositoryImpl;
import com.ben.words.ui.add_new_verb.AddVerbActivity;
import com.ben.words.ui.add_new_word.AddWordActivity;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenterImpl implements MainPresenter<MainView> {

    private MainView view;

    @Inject
    public MainPresenterImpl() {}

    @Override
    public void addNewItem(int action) {
        if (action == MainActivity.Action.WORD.getAction()) {
            view.moveToScreenWithBack(AddWordActivity.class);
        }else if (action == MainActivity.Action.VERB.getAction()) {
            view.moveToScreenWithBack(AddVerbActivity.class);
        }
    }

    @Override
    public void attachPresenter(MainView mainView) {
        this.view = mainView;
    }

    @Override
    public void detachPresenter() {
        view = null;
    }

    //@Override
    public void test() {
        Repository repository = new RealmWordRepositoryImpl();

        /*Word word = new Word();
        word.setPartsOfSpeech("adj");
        word.setValue("test");
        word.setTranslates("тест");
        word.setTranscription("тест");

        Word word1 = new Word();
        word1.setPartsOfSpeech("noun");
        word1.setValue("demo");
        word1.setTranslates("демо");
        word1.setTranscription("дэмо");*/

        CompositeDisposable disposable = new CompositeDisposable();

        /*disposable.add((Disposable) repository.addNewItem(word)
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
                }));*/

        view.moveToScreenWithoutBack(AddWordActivity.class);
    }
}
