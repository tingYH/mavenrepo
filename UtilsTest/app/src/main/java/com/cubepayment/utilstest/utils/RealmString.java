package com.cubepayment.utilstest.utils;

import io.realm.RealmObject;

/**
 * @author ChengKai YH
 * @version $
 * <p/>
 * <p/> $
 */
public class RealmString extends RealmObject {
    private String string;

    public RealmString() {
    }

    public RealmString(String str) {
        this.string = str;
    }

    public void setString(String string) {
        this.string = string;
    }
}
