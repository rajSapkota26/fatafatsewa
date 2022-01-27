package com.fatafatsewa.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatafatsewa.R;
import com.fatafatsewa.adapter.ProductAdapter;
import com.fatafatsewa.demolist.Demolist;
import com.fatafatsewa.model.Product;

import java.util.List;


public class MainCategoryFragment extends Fragment {
    private String catname;

    public MainCategoryFragment(String catname) {
        this.catname = catname;
    }

    //product list
    private List<Product> products;
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_category, container, false);
        products= Demolist.getAllProductItem();
        recyclerView=view.findViewById(R.id.cat_product_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        productAdapter=new ProductAdapter(getContext(),products);
        recyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
        return view;
    }
}