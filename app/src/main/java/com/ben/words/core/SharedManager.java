package com.ben.words.core;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedManager {

    private static final String APP_PREFS = "SharedPrefs";
    private static final String WORD_SYNC_STATE = "WordSyncState";
    private static final String VERB_SYNC_STATE = "VerbSyncState";

    @Inject
    Context context;

    private static SharedManager instance;

    private static SharedPreferences sharedPreferences;

    private SharedManager() {
        App.getAppInjector().inject(this);
        sharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }

    public static SharedManager getInstance() {
        if (instance != null) {
            return instance;
        }else {
            instance = new SharedManager();
            return instance;
        }
    }

    public void setWordSyncState(boolean val) {
        sharedPreferences.edit().putBoolean(WORD_SYNC_STATE, val).apply();
    }

    public boolean getWordSyncState() {
        return sharedPreferences.getBoolean(WORD_SYNC_STATE, false);
    }

    public void setVerbSyncState(boolean val) {
        sharedPreferences.edit().putBoolean(VERB_SYNC_STATE, val).apply();
    }

    public boolean getVerbSyncState() {
        return sharedPreferences.getBoolean(VERB_SYNC_STATE, false);
    }
}
