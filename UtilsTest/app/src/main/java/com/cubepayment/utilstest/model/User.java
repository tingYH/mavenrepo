package com.cubepayment.utilstest.model;

import com.cubepayment.utilstest.ViewTypeInterface;
import com.cubepayment.utilstest.utils.RealmString;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author ChengKai YH
 * @version $
 * <p/>
 * <p/> $
 */
public class User extends RealmObject implements ViewTypeInterface {
    @PrimaryKey
    private long id;
    private String firstName;
    private String email;

    private RealmList<RealmString> contactList;

    public User(){contactList = new RealmList<>();}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RealmList<RealmString> getContactList() {
        return contactList;
    }

    public void setContactList(RealmList<RealmString> contactList) {
        this.contactList = contactList;
    }
}
