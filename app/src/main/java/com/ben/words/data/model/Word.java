package com.ben.words.data.model;

import java.util.List;
import java.util.Objects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Word extends RealmObject {

    @PrimaryKey
    private long id;
    private String partsOfSpeech;
    private String value;
    private String transcription;
    private RealmList<Translate> translates;

    public Word() {
        this.id = hashCode();
    }

    public long getId() {
        return id;
    }

    public String getPartsOfSpeech() {
        return partsOfSpeech;
    }

    public void setPartsOfSpeech(String partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
        this.id = hashCode();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.id = hashCode();
    }

    public RealmList<Translate> getTranslates() {
        return translates;
    }

    public void setTranslates(RealmList<Translate> translates) {
        this.translates = translates;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(partsOfSpeech, word.partsOfSpeech) &&
                Objects.equals(value, word.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(partsOfSpeech, value);
    }

    public String getTranslateValue() {
        StringBuilder result = new StringBuilder();

        if (translates != null && translates.size() >0) {
            for (int x = 0; x <= translates.size()-1; x++) {
                result.append(translates.get(x).getValue());
                if (x < translates.size()-1) {
                    result.append(", ");
                }
            }
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return value;
    }
}
