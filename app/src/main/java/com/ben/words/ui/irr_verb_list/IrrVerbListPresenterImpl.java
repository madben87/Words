package com.ben.words.ui.irr_verb_list;

import com.ben.words.core.App;
import com.ben.words.data.model.IrregularVerb;
import com.ben.words.data.model.Word;
import com.ben.words.data.realm_db.RealmIrrVerbRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class IrrVerbListPresenterImpl implements IrrVerbListPresenter<IrrVerbListView> {

    @Inject
    RealmIrrVerbRepository repository;

    private IrrVerbListView view;
    private CompositeDisposable disposable;

    public IrrVerbListPresenterImpl() {
        App.getAppInjector().inject(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void attachPresenter(IrrVerbListView irrVerbListView) {
        this.view = irrVerbListView;
    }

    @Override
    public void detachPresenter() {
        view = null;
        disposable.clear();
    }

    @Override
    public void updateList() {
        disposable.add((Disposable) repository.getList(IrregularVerb.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<IrregularVerb>>() {
                    @Override
                    public void onNext(List<IrregularVerb> list) {
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
