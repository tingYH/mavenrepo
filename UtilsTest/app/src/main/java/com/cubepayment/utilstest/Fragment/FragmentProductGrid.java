package com.cubepayment.utilstest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cubepayment.utilstest.DataGenerator;
import com.cubepayment.utilstest.R;
import com.cubepayment.utilstest.adapter.AdapterGridShopProductCard;
import com.cubepayment.utilstest.model.ShopProduct;
import com.cubepayment.utilstest.utils.SpacingItemDecoration;
import com.cubepayment.utilstest.utils.Tools;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;

public class FragmentProductGrid extends Fragment {

    public FragmentProductGrid() {
    }

    public static FragmentProductGrid newInstance() {
        FragmentProductGrid fragment = new FragmentProductGrid();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_product_grid, container, false);


        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getActivity(), 8), true));
        recyclerView.setHasFixedSize(true);

        List<ShopProduct> items = DataGenerator.getShoppingProduct(getActivity());
        Collections.shuffle(items);

        //set data and list adapter
        AdapterGridShopProductCard mAdapter = new AdapterGridShopProductCard(getActivity(), items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterGridShopProductCard.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ShopProduct obj, int position) {
                Snackbar.make(root, "Item " + obj.title + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnMoreButtonClickListener(new AdapterGridShopProductCard.OnMoreButtonClickListener() {
            @Override
            public void onItemClick(View view, ShopProduct obj, MenuItem item) {
                Snackbar.make(root, obj.title + " (" + item.getTitle() + ") clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}