package com.ben.words.data.realm_db;

import com.ben.words.data.model.Translate;
import com.ben.words.data.model.Word;
import com.ben.words.util.KeyGenerator;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmDBManager implements RealmRepository<Word> {

    private Realm realm;
    private Word res;

    @Override
    public Observable<List<Word>> getList(Class<Word> cls) {

        realm = Realm.getDefaultInstance();

        RealmResults<Word> results = realm.where(Word.class).findAll()/*.sort("published", Sort.DESCENDING)*/;

        return Observable.just(realm.copyFromRealm(results))
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        realm.close();
                    }
                });
    }

    @Override
    public Observable<Word> getItem(long id) {
        return null;
    }

    @Override
    public Observable<Word> addNewItem(final Word word) {
        return Observable.just(word.getClass())
                .flatMap(new Function<Class<? extends Word>, Observable<? extends Word>>() {
                    @Override
                    public Observable<? extends Word> apply(Class<? extends Word> aClass) throws Exception {
                        return Observable.just(test());
                    }
                });
    }

    private Word test() {
        realm = Realm.getDefaultInstance();
        //realm.beginTransaction();

        /*Word word = new Word();
        word.setPartsOfSpeech("adj");
        word.setValue("test");
        //word.setTranslates("тест");
        word.setTranscription("тест");*/

        //Translate translate = new Translate();
        //translate.setValue("тест");
        //final Translate savedTranslate = realm.copyToRealm(translate);
//
        //Word w = realm.createObject(Word.class, KeyGenerator.generateKey("adj", "test"));
        //w.getTranslates().add(savedTranslate);
        //w.setValue("test");
        //w.setPartsOfSpeech("adj");
        //w.setTranscription("тест");
        //realm.copyToRealmOrUpdate(w);
        //realm.commitTransaction();
        //realm.close();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Translate translate = new Translate();
                translate.setValue("тест");
                Translate savedTranslate = realm.copyToRealmOrUpdate(translate);
                Word w = realm.createObject(Word.class, KeyGenerator.generateKey("adj", "test"));
                w.getTranslates().add(savedTranslate);
                w.setValue("test");
                w.setPartsOfSpeech("adj");
                w.setTranscription("тест");
                res = realm.copyToRealmOrUpdate(w);
            }
        });

        realm.close();

        return res;
    }

    @Override
    public void addList(List<Word> list) {

    }

    @Override
    public void updateItem(Word word) {

    }

    @Override
    public void updateList(List<Word> list) {

    }

    @Override
    public void removeItem(long id) {

    }
}


/*
.doOnSubscribe(new Consumer<Disposable>() {
@Override
public void accept(Disposable disposable) throws Exception {
        realm.beginTransaction();
        }
        })
        .doOnDispose(new Action() {
@Override
public void run() throws Exception {
        realm.commitTransaction();
        realm.close();
        }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()*/
