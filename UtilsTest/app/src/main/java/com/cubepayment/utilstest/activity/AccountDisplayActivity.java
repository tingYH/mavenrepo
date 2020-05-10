package com.cubepayment.utilstest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cubepayment.a8device.ActionInterface;
import com.cubepayment.a8device.ActionManager;
import com.cubepayment.utilstest.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.net.URL;
import java.net.URLClassLoader;

public class AccountDisplayActivity extends AppCompatActivity {
    private Button bt_permission;
    private TextView tv_account, tv_account_type;
    private boolean googleserviceFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_display);
        final ActionInterface actionInterface = new ActionManager();
        tv_account = findViewById(R.id.tv_account);
        tv_account_type = findViewById(R.id.tv_account_type);
        bt_permission = findViewById(R.id.bt_permission);
        bt_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionInterface.actionPrint("file:/storage/emulated/0/Pictures/demo.png");
//
//                AccountManager accountManager = AccountManager.get(AccountDisplayActivity.this);
//                Account[] accounts = accountManager.getAccounts();
//                for (Account account : accounts) {
//                    tv_account.setText(account.name);
//                    tv_account_type.setText(account.type);
//                    Log.i("--Get Account Example--", account.name);
//                    Log.i("--Get Account Example--", account.type);
//                }
            }
        });
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(this, resultCode, 2404).show();
            }
            googleserviceFlag = false;
        }
        if (googleserviceFlag == false) {
            tv_account.setText("不支援Google Play (not support google play service)");
            tv_account_type.setText("不支援Google Play (not support google play service)");
            try {
                actionInterface.actionRegister(this,true);
//                actionInterface.actionUnregister();
                //第二种
//                URL url1 = new URL("file:D:/jar_lib/usdk_api_aidl_v2.3.3.20190628.jar");
//                URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url1 }, Thread.currentThread()
//                        .getContextClassLoader());
//                Class<?> myClass1 = myClassLoader1.loadClass("com.java.jarloader.UDeviceService");
//                Log.e("Tin", "myClass1: load OK" + myClassLoader1.getCanonicalName());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
