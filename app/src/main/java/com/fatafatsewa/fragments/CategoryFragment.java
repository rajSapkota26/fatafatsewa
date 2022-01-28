package com.fatafatsewa.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fatafatsewa.R;
import com.fatafatsewa.adapter.CategoryAdapterOnlyName;
import com.fatafatsewa.model.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Category> categories;
    CategoryAdapterOnlyName mainCategoryAdapter;
    private String catName="";
    InnerCategoryFragment mainCategoryFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.cat_cat_recyclerview);
        categories = new ArrayList<>();
        categories.add(new Category("Mobile", 0));
        categories.add(new Category("Laptop", 0));
        categories.add(new Category("Bar", 0));
        categories.add(new Category("Home applience", 0));
        categories.add(new Category("Camera ", 0));
        categories.add(new Category("Machinery", 0));
        categories.add(new Category("Accessories", 0));
        categories.add(new Category("Stationary", 0));
        mainCategoryAdapter = new CategoryAdapterOnlyName(getContext(), categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mainCategoryAdapter);
        mainCategoryAdapter.notifyDataSetChanged();
        mainCategoryFragment=new InnerCategoryFragment(catName);
        getParentFragmentManager().beginTransaction().replace(R.id.cat_fragment_container, mainCategoryFragment).commit();
        mainCategoryAdapter.setViewItemInterface(new CategoryAdapterOnlyName.RecyclerViewItemInterface() {
            @Override
            public void onItemClick(int position, String c) {

                catName=c;
                Toast.makeText(getContext(), position+" "+catName, Toast.LENGTH_SHORT).show();
                getParentFragmentManager().beginTransaction().replace(R.id.cat_fragment_container, new InnerCategoryFragment(c)).commit();

            }
        });




        return view;
    }
}