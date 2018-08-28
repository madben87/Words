package com.ben.words.service;

import android.app.IntentService;
import android.content.Intent;

import com.ben.words.core.App;
import com.ben.words.core.SharedManager;
import com.ben.words.data.model.IrregularVerb;
import com.ben.words.data.model.Word;
import com.ben.words.data.realm_db.FireBaseDBHelper;
import com.ben.words.data.realm_db.RealmDBHelper;
import com.ben.words.data.realm_db.RealmIrrVerbRepository;
import com.ben.words.data.realm_db.RealmWordRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DBSyncIntentService extends IntentService {

    @Inject
    RealmWordRepository wordRepository;
    @Inject
    RealmIrrVerbRepository verbRepository;

    private CompositeDisposable compositeDisposable;

    public DBSyncIntentService() {
        super("DBSyncIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        compositeDisposable = new CompositeDisposable();
        App.getAppInjector().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        compositeDisposable.add((Disposable) wordRepository.getList(Word.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /*.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        RealmDBHelper.openRealm();
                    }
                })
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        RealmDBHelper.closeRealm();
                    }
                })*/
                .subscribeWith(new DisposableObserver<List<Word>>() {
                    @Override
                    public void onNext(List<Word> list) {
                        if (list != null && list.size() > 0) {
                            for (Word elem : list) {
                                FireBaseDBHelper.addWord(elem);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = e.getMessage();
                        s.isEmpty();
                    }

                    @Override
                    public void onComplete() {
                        String s = " ";
                    }
                })
        );

        if (SharedManager.getInstance().getWordSyncState()) {


        }

        if (SharedManager.getInstance().getVerbSyncState()) {


        }

        syncWords();
    }

    private void syncWords(/*List<Word> list*/) {


        SharedManager.getInstance().setWordSyncState(false);
    }

    private void syncVerbs(List<IrregularVerb> list) {

        SharedManager.getInstance().setVerbSyncState(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
