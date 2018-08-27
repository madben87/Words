package com.ben.words.ui.add_new;

import com.ben.words.core.App;
import com.ben.words.data.Repository;
import com.ben.words.data.model.Word;
import com.ben.words.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddWordPresenterImpl implements AddWordPresenter<AddWordView> {

    private AddWordView view;
    private CompositeDisposable disposable;

    @Inject
    Repository repository;

    public AddWordPresenterImpl() {
        App.getAppInjector().inject(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void attachPresenter(AddWordView view) {
        this.view = view;
    }

    @Override
    public void detachPresenter() {
        view = null;
        disposable.clear();
    }

    @Override
    public void addNewItem(Word word) {
        disposable.add((Disposable) repository.addNewItem(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Word>() {
                    @Override
                    public void onNext(Word w) {
                        /*if (w != null && view != null) {
                            view.showMessage(w.getValue() + " is added");
                        }*/
                        //Add EventBus
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.ADD_NEW_ITEM));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
