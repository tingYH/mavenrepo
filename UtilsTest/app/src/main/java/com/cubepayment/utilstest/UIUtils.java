package com.cubepayment.utilstest;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import com.cubepayment.utilstest.activity.DisplayDatabaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.realm.Realm;

public class UIUtils {

    private static Toast toast;

    /**
     * 靜態吐司
     *
     * @param context
     * @param text
     */
    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }

    /**
     * 不需要上下文物件的  靜態toast
     */
//    public static void showToast(String text) {
//        showToast(getContext(), text);
//    }

    /**
     * 獲取上下文物件
     *
     * @return
     */
//    public static Context getContext() {
//        return DisplayDatabaseActivity.getInstance();
//    }

    /**
     * 獲得Realm例項
     *
     * @return
     */
//    public static Realm getRealmInstance() {
//        return Realm.getInstance(DisplayDatabaseActivity.getRealmConfiguration());
//    }

    /**
     * 獲取Realm資料庫64位祕鑰
     *
     * @param key
     * @return
     */
    public static byte[] getRealmKey(String key) {
        String newKey = "";
        for (int i = 0; i < 4; i++) {
            newKey = newKey + key;
        }
        return newKey.getBytes();
    }

    /**
     * 從asset路徑下讀取對應檔案轉String輸出
     *
     * @return
     */
//    public static String getJson(String fileName) {
//        StringBuilder sb = new StringBuilder();
//        AssetManager am = getContext().getAssets();
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new InputStreamReader(
//                    am.open(fileName)));
//            String next = "";
//            while (null != (next = br.readLine())) {
//                sb.append(next);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            sb.delete(0, sb.length());
//        } finally {
//            try {
//                if (br != null) {
//                    br.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString().trim();
//    }

}
