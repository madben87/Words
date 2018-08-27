package com.ben.words.data.realm_db;

import com.ben.words.data.Repository;

import io.realm.RealmObject;

public interface RealmWordRepository<D extends RealmObject> extends Repository<D> {
}
