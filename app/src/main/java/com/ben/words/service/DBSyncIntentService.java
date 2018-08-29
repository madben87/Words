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
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class DBSyncIntentService extends IntentService {

    @Inject
    RealmWordRepository wordRepository;
    @Inject
    RealmIrrVerbRepository verbRepository;
    private Realm wordRealm;
    private Realm verbRealm;

    public DBSyncIntentService() {
        super("DBSyncIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.getAppInjector().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (SharedManager.getInstance().getWordSyncState()) {
            wordRealm = Realm.getDefaultInstance();

            List<Word> resList;

            if (wordRealm != null) {

                RealmResults<Word> results = wordRealm.where(Word.class).findAll();

                resList = wordRealm.copyFromRealm(results);

                for (Word elem : resList) {
                    FireBaseDBHelper.addWord(elem);
                }

                if (wordRealm != null) {
                    wordRealm.close();
                }
            }
        }

        if (SharedManager.getInstance().getVerbSyncState()) {
            verbRealm = Realm.getDefaultInstance();

            List<IrregularVerb> resList;

            if (verbRealm != null) {

                RealmResults<IrregularVerb> results = verbRealm.where(IrregularVerb.class).findAll();

                resList = verbRealm.copyFromRealm(results);

                for (IrregularVerb elem : resList) {
                    FireBaseDBHelper.addVerbs(elem);
                }

                if (verbRealm != null) {
                    verbRealm.close();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
