package com.ben.words.data.realm_db;

import com.ben.words.data.model.Word;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class RealmWordRepositoryImpl implements RealmWordRepository<Word> {

    @Override
    public Observable<List<Word>> getList(Class<Word> cls) {
        return Observable.just(cls)
                .flatMap(new Function<Class<Word>, ObservableSource<? extends List<Word>>>() {
                    @Override
                    public ObservableSource<? extends List<Word>> apply(Class<Word> wordClass) throws Exception {
                        return Observable.just(RealmDBHelper.getWordsList());
                    }
                });
    }

    @Override
    public Observable<Word> getItem(final long id) {
        return Observable.just(Word.class)
                .flatMap(new Function<Class<Word>, Observable<? extends Word>>() {
                    @Override
                    public Observable<? extends Word> apply(Class<Word> wordClass) throws Exception {
                        return Observable.just(RealmDBHelper.getWord(id));
                    }
                });
    }

    @Override
    public Observable<Word> addNewItem(final Word word) {
        return Observable.just(word.getClass())
                .flatMap(new Function<Class<? extends Word>, Observable<? extends Word>>() {
                    @Override
                    public Observable<? extends Word> apply(Class<? extends Word> aClass) throws Exception {
                        return Observable.just(RealmDBHelper.addWord(word));
                    }
                });
    }

    @Override
    public void addList(List<Word> list) {

    }

    @Override
    public void updateItem(Word word) {

    }

    @Override
    public void updateList(List<Word> list) {

    }

    @Override
    public void removeItem(long id) {

    }
}
