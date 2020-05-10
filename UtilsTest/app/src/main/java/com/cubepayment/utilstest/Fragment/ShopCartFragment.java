package com.cubepayment.utilstest.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cubepayment.utilstest.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ShopCartFragment extends Fragment {
    private static ShopCartFragment mInstance;
    private View rootView;
    public View parent_view;
    private ViewPager view_pager;
    private TabLayout tab_layout;
    public static ShopCartFragment newInstance() {
        if (mInstance == null) {
            mInstance = new ShopCartFragment();
        }
        return mInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_shop_cart, container, false);

        view_pager = rootView.findViewById(R.id.view_pager);
        setupViewPager(view_pager);

        tab_layout = rootView.findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(view_pager);
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(FragmentProductGrid.newInstance(), "WOMEN");
        adapter.addFragment(FragmentProductGrid.newInstance(), "MEN");
        adapter.addFragment(FragmentProductGrid.newInstance(), "YOUNG BOYS");
        adapter.addFragment(FragmentProductGrid.newInstance(), "YOUNG GIRLS");
        adapter.addFragment(FragmentProductGrid.newInstance(), "ALL TIME");
        viewPager.setAdapter(adapter);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
