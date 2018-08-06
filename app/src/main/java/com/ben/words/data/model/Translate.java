package com.ben.words.data.model;

import io.realm.RealmObject;

public class Translate extends RealmObject {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
