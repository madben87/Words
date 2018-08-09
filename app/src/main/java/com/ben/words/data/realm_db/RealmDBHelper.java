package com.ben.words.data.realm_db;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.ben.words.data.model.Translate;
import com.ben.words.data.model.Word;
import com.ben.words.util.KeyGenerator;

import io.realm.Realm;

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
                    Translate translate = new Translate();

                    translate.setValue("тест");

                    Translate savedTranslate = realm.copyToRealmOrUpdate(translate);

                    if (item != null && !item.getValue().isEmpty() && !item.getPartsOfSpeech().isEmpty()) {
                        Word w = realm.createObject(Word.class, KeyGenerator.generateKey(item.getPartsOfSpeech(), item.getValue()));

                        if (!translate.getValue().isEmpty()) {
                            w.getTranslates().add(savedTranslate);
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
}
