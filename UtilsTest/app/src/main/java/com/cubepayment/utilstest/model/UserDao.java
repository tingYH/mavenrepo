package com.cubepayment.utilstest.model;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * @author ChengKai YH
 * @version $
 * <p/>
 * <p/> $
 */
public class UserDao {

    private Realm mRealm;

    public UserDao(Realm relam) {
        mRealm = relam;
    }

    public void save(final RealmObject user) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(user);
            }
        });
    }

    public void save(final List<User> userList) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("Tin","size"+userList.size());
                realm.copyToRealmOrUpdate(userList);
            }
        });
    }

    public RealmResults<User> loadAll() {
        return mRealm.where(User.class).findAllSorted("id");
    }

    public RealmResults<User> loadAllAsync() {
        return mRealm.where(User.class).findAllSortedAsync("id");
    }

    public RealmObject loadBy(long id) {
        return mRealm.where(User.class).equalTo("id", id).findFirst();
    }

    public void remove(final RealmObject object) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                object.deleteFromRealm();
            }
        });
    }

    public void removeAll() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                mRealm.clear(User.class);
                mRealm.delete(User.class);
            }
        });
    }

    public long count() {
        return mRealm.where(User.class).count();
    }

}
