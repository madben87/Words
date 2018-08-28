package com.ben.words.ui.add_new_verb;

import com.ben.words.core.App;
import com.ben.words.core.SharedManager;
import com.ben.words.data.model.IrregularVerb;
import com.ben.words.data.model.Word;
import com.ben.words.data.realm_db.RealmIrrVerbRepository;
import com.ben.words.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddVerbPresenterImpl implements AddVerbPresenter<AddVerbView> {

    @Inject
    RealmIrrVerbRepository repository;

    private AddVerbView view;
    private CompositeDisposable disposable;

    public AddVerbPresenterImpl() {
        App.getAppInjector().inject(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void attachPresenter(AddVerbView addVerbView) {
        this.view = addVerbView;
    }

    @Override
    public void detachPresenter() {
        this.view = null;
        disposable.clear();
    }

    @Override
    public void addNewItem(IrregularVerb verb) {
        disposable.add((Disposable) repository.addNewItem(verb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<IrregularVerb>() {
                    @Override
                    public void onNext(IrregularVerb v) {

                        SharedManager.getInstance().setVerbSyncState(true);
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
