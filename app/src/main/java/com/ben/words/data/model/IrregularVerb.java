package com.ben.words.data.model;

import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class IrregularVerb extends RealmObject {

    @PrimaryKey
    private long id;
    private String firstForm;
    private String secondForm;
    private String thirdForm;
    private Translate translate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstForm() {
        return firstForm;
    }

    public void setFirstForm(String firstForm) {
        this.firstForm = firstForm;
    }

    public String getSecondForm() {
        return secondForm;
    }

    public void setSecondForm(String secondForm) {
        this.secondForm = secondForm;
    }

    public String getThirdForm() {
        return thirdForm;
    }

    public void setThirdForm(String thirdForm) {
        this.thirdForm = thirdForm;
    }

    public Translate getTranslate() {
        return translate;
    }

    public void setTranslate(Translate translate) {
        this.translate = translate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IrregularVerb that = (IrregularVerb) o;
        return Objects.equals(firstForm, that.firstForm) &&
                Objects.equals(translate.getValue(), that.translate.getValue());
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstForm, translate.getValue());
    }

    @Override
    public String toString() {
        return firstForm;
    }
}
