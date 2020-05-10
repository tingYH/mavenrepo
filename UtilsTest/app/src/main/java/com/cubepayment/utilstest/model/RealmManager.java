package com.cubepayment.utilstest.model;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author ChengKai YH
 * @version $
 * <p/>
 * <p/> $
 */
public class RealmManager {
    private static Realm mRealm;

    public static Realm open() {

        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    public static void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    public static UserDao createUserDao() {
        checkForOpenRealm();
        return new UserDao(mRealm);
    }

    public static void clear() {
        checkForOpenRealm();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(User.class);
            }
        });
    }

    private static void checkForOpenRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }
}
