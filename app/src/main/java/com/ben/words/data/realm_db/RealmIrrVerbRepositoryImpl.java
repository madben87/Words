package com.ben.words.data.realm_db;

import com.ben.words.data.model.IrregularVerb;
import com.ben.words.data.model.Word;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class RealmIrrVerbRepositoryImpl implements RealmIrrVerbRepository<IrregularVerb> {
    @Override
    public Observable<List<IrregularVerb>> getList(Class<IrregularVerb> cls) {
        return Observable.just(cls)
                .flatMap(new Function<Class<IrregularVerb>, ObservableSource<? extends List<IrregularVerb>>>() {
                    @Override
                    public ObservableSource<? extends List<IrregularVerb>> apply(Class<IrregularVerb> wordClass) throws Exception {
                        return Observable.just(RealmDBHelper.getIrrVerbList());
                    }
                });
    }

    @Override
    public Observable<IrregularVerb> getItem(long id) {
        return null;
    }

    @Override
    public Observable<IrregularVerb> addNewItem(final IrregularVerb irregularVerb) {
        return Observable.just(irregularVerb.getClass())
                .flatMap(new Function<Class<? extends IrregularVerb>, Observable<? extends IrregularVerb>>() {
                    @Override
                    public Observable<? extends IrregularVerb> apply(Class<? extends IrregularVerb> aClass) throws Exception {
                        return Observable.just(RealmDBHelper.addNewIrrVerb(irregularVerb));
                    }
                });
    }

    @Override
    public void addList(List<IrregularVerb> list) {

    }

    @Override
    public void updateItem(IrregularVerb irregularVerb) {

    }

    @Override
    public void updateList(List<IrregularVerb> list) {

    }

    @Override
    public void removeItem(long id) {

    }
}
