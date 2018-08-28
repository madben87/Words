package com.ben.words.modules;

import com.ben.words.data.model.Word;
import com.ben.words.data.realm_db.RealmIrrVerbRepository;
import com.ben.words.data.realm_db.RealmIrrVerbRepositoryImpl;
import com.ben.words.data.realm_db.RealmWordRepository;
import com.ben.words.data.realm_db.RealmWordRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    RealmWordRepository providesWordRepository() {
        return new RealmWordRepositoryImpl();
    }

    @Provides
    @Singleton
    RealmIrrVerbRepository providesIrrVerbRepository() {
        return new RealmIrrVerbRepositoryImpl();
    }
}
