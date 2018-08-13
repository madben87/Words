package com.ben.words.data.realm_db;

import android.annotation.SuppressLint;

import com.ben.words.data.model.Translate;
import com.ben.words.data.model.Word;
import com.ben.words.util.KeyGenerator;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmDBHelper {

    @SuppressLint("StaticFieldLeak")
    private static Realm realm;
    private static Word wordRes;

    public static Word addItem(final Word item) {
        realm = Realm.getDefaultInstance();

        if (realm != null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    List<Translate> savedTranslates = new RealmList<>();

                    if (item.getTranslates() != null && item.getTranslates().size() > 0) {
                        for (Translate elem : item.getTranslates()) {
                            elem.setId(KeyGenerator.generateKey(item.getValue(), elem.getValue()));
                            savedTranslates.add(realm.copyToRealmOrUpdate(elem));
                        }
                    }

                    if (item != null && !item.getValue().isEmpty() && !item.getPartsOfSpeech().isEmpty()) {
                        Word w = realm.createObject(Word.class, KeyGenerator.generateKey(item.getPartsOfSpeech(), item.getValue()));

                        if (savedTranslates.size() > 0) {
                            w.setTranslates((RealmList<Translate>) savedTranslates);
                        }
                        w.setValue(item.getValue());
                        w.setPartsOfSpeech(item.getPartsOfSpeech());
                        if (!item.getTranscription().isEmpty()) {
                            w.setTranscription(item.getTranscription());
                        }
                        wordRes = realm.copyToRealmOrUpdate(w);
                    }
                }
            });

            realm.close();
        }

        return wordRes;
    }

    public static Word getItem(long id) {
        realm = Realm.getDefaultInstance();

        if (realm != null) {
            wordRes = realm.where(Word.class).equalTo("id", id).findFirst();

            realm.close();
        }

        return wordRes;
    }

    public static List<Word> getList() {
        realm = Realm.getDefaultInstance();
        List<Word> resList = new RealmList<>();

        if (realm != null) {

            RealmResults<Word> results = realm.where(Word.class).findAll();

            resList = realm.copyFromRealm(results);

            realm.close();
        }
        return resList;
    }
}
