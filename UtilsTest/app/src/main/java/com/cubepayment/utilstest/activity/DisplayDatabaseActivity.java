package com.cubepayment.utilstest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cubepayment.utilstest.Fragment.FriendListFragment;
import com.cubepayment.utilstest.R;
import com.cubepayment.utilstest.Fragment.ShopCartFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DisplayDatabaseActivity extends AppCompatActivity {

    private View ll_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_database);
        initView();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Tin", "onStart");
    }

    private void initView() {
        FloatingActionButton fb_friend_list = findViewById(R.id.fb_friend_list);
        fb_friend_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(FriendListFragment.getInstance(), "FriendList");
            }
        });

        FloatingActionButton fb_friend_chat = findViewById(R.id.fb_friend_chat);
        FloatingActionButton fb_shop = findViewById(R.id.fb_shop);
        fb_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(ShopCartFragment.newInstance(), "ShopCart");
            }
        });
        FloatingActionButton fb_diary = findViewById(R.id.fb_diary);
        FloatingActionButton fb_setting = findViewById(R.id.fb_setting);
    }

    private void openFragment(Fragment replaceFragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.ll_container, replaceFragment);
//        ft.commitAllowingStateLoss();
        ft.commit();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Tin", "onDestroy");
    }
}
