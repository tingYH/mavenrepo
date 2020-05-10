package com.cubepayment.tintestlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            URL url1 = new URL("https://exp-internal.radiumone.io/usdk_api_aidl_v2.3.3.20190628.jar");
            URL url2 = new URL("https://exp-internal.radiumone.io/interface_jar.jar");
//            URL url1 = new URL("file:D:/jar_lib/usdk_api_aidl_v2.3.3.20190628.jar");
//            URL url2 = new URL("file:D:/lib_jar/test.jar");

            final URLClassLoader myClassLoader1 = new URLClassLoader(new URL[]{url1, url2}, Thread.currentThread()
                    .getContextClassLoader());
            final Class<?> myClass1 = myClassLoader1.loadClass("com.cubepayment.a8device.ActionManager");
            final Object instance = myClass1.newInstance();
            Method method = myClass1.getDeclaredMethod("actionRegister", Context.class, boolean.class);
            method.invoke(instance, this, true);


            findViewById(R.id.bt_print).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Method method2 = myClass1.getDeclaredMethod("actionPrint", String.class);
                        method2.invoke(instance,"file:/storage/emulated/0/Pictures/demo.png");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
