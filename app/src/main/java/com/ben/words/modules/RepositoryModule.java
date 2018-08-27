package com.ben.words.modules;

import com.ben.words.data.Repository;
import com.ben.words.data.realm_db.RealmRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    Repository providesRepository() {
        return new RealmRepositoryImpl();
    }
}
