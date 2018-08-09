package com.ben.words.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Translate extends RealmObject {

    @PrimaryKey
    private long id;
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
