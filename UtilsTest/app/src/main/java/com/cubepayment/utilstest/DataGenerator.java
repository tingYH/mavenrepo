package com.cubepayment.utilstest;

import android.content.Context;
import android.content.res.TypedArray;

import com.cubepayment.utilstest.model.ShopProduct;
import com.cubepayment.utilstest.model.User;
import com.cubepayment.utilstest.utils.RealmString;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class DataGenerator {

    public static List<User> generateUserList() {
        List<User> userList = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            User user = new User();
            user.setId(i);
            user.setFirstName("John" + i);
            user.setEmail(String.format("JohnDoe%d@gmail.com", i));
            user.getContactList().addAll(generateContactList());

            userList.add(user);
        }

        return userList;
    }

    public static User generateUser() {
        User user = new User();
        user.setId(1);
        user.setEmail("user@gmail.com");
        user.setFirstName("John");
        user.setContactList(generateContactList());
        return user;
    }

    private static RealmList<RealmString> generateContactList() {
        RealmList<RealmString> contactList = new RealmList<>();
        for (int i = 0; i < 3; i++) {
            contactList.add(new RealmString("Contact " + i));
        }
        return contactList;
    }

    public static List<ShopProduct> getShoppingProduct(Context ctx) {
        List<ShopProduct> items = new ArrayList<>();
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.shop_product_image);
        String title_arr[] = ctx.getResources().getStringArray(R.array.shop_product_title);
        String price_arr[] = ctx.getResources().getStringArray(R.array.shop_product_price);
        for (int i = 0; i < drw_arr.length(); i++) {
            ShopProduct obj = new ShopProduct();
            obj.image = drw_arr.getResourceId(i, -1);
            obj.title = title_arr[i];
            obj.price = price_arr[i];
            obj.imageDrw = ctx.getResources().getDrawable(obj.image);
            items.add(obj);
        }
        return items;
    }

}
