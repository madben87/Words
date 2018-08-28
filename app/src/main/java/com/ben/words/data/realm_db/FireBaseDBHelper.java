package com.ben.words.data.realm_db;

import android.support.annotation.NonNull;

import com.ben.words.data.model.Word;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FireBaseDBHelper {

    private static final String APP_DB = "words_app";
    private static final String WORDS_TABLE = "words";
    private static final String VERBS_TABLE = "verbs";
    private static final String TRANSLATES_TABLE = "translates";
    private static final String USER_UID = "1";

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = database.getReference(APP_DB);

    public static List<Word> getWords() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return null;
    }

    public static void addWord(Word word) {
        databaseReference.child(USER_UID).child(WORDS_TABLE).child(String.valueOf(word.getId())).setValue(word);
    }
}
