package com.cubepayment.utilstest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cubepayment.utilstest.R;
import com.facebook.stetho.Stetho;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());


    }

    public void dateOnclick(View view) {
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTheme(R.style.Widget_AppCompat_ActionBar);
        MaterialDatePicker picker = builder.build();
        picker.show(getSupportFragmentManager(),picker.toString());
    }

    public void goDatabaseOnclick(View view) {
        startActivity(new Intent(this, DisplayDatabaseActivity.class));
    }

    public void getAccountOnclick(View view) {
        startActivity(new Intent(this, AccountDisplayActivity.class));
    }
}
