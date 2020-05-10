package com.cubepayment.utilstest.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cubepayment.utilstest.DataGenerator;
import com.cubepayment.utilstest.R;
import com.cubepayment.utilstest.adapter.UserAdapter;
import com.cubepayment.utilstest.model.RealmManager;
import com.cubepayment.utilstest.model.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class FriendListFragment extends Fragment {

    private static FriendListFragment sInstance;
    private View root_view;
    private UserAdapter mUserAdapter;
    private RecyclerView rl_user_list;

    public FriendListFragment() {
        // Required empty public constructor
    }

    public static FriendListFragment getInstance() {
        if (sInstance == null) {
            sInstance = new FriendListFragment();
        }
        return sInstance;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Realm.init(getContext());
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder().name("utils.realm").build();
//                    new RealmConfiguration.Builder(this)
//                        .name("realm-sample.db")
//                        .schemaVersion(1)
//                        .build();

        Realm.setDefaultConfiguration(realmConfiguration);
        RealmManager.open();
        root_view = inflater.inflate(R.layout.fragment_friend_list, container, false);
        mUserAdapter = new UserAdapter();
        rl_user_list = root_view.findViewById(R.id.rv_dataDisplay);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rl_user_list.setHasFixedSize(true);
        rl_user_list.setItemAnimator(new DefaultItemAnimator());
        rl_user_list.setLayoutManager(layoutManager);
        rl_user_list.setAdapter(mUserAdapter);
        saveUserList();
        loadUserListAsync();
        return root_view;

    }

    private void loadUserListAsync() {
        final RealmResults<User> dataList = RealmManager.createUserDao().loadAllAsync();
        dataList.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange(Object element) {
                updateRecyclerView(dataList);
            }
        });
    }

    private void updateRecyclerView(List<User> userList) {
        if (mUserAdapter != null && userList != null) {
            mUserAdapter.setData(userList);
            mUserAdapter.notifyDataSetChanged();
        }
    }

    private void saveUserList() {
        RealmManager.createUserDao().save(DataGenerator.generateUserList());
        RealmManager.createUserDao().loadAllAsync();
    }

}
