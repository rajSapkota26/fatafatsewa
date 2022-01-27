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
import com.fatafatsewa.adapter.MainCategoryAdapter;
import com.fatafatsewa.model.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Category> categories;
    MainCategoryAdapter mainCategoryAdapter;
    private String catName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.cat_cat_recyclerview);
        categories = new ArrayList<>();
        categories.add(new Category("Category 1 one", 0));
        categories.add(new Category("Category 1 two", 0));
        categories.add(new Category("Category 1 three", 0));
        categories.add(new Category("Category 1 four", 0));
        categories.add(new Category("Category 1 five ", 0));
        categories.add(new Category("Category 1 six", 0));
        categories.add(new Category("Category 1 seven", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        categories.add(new Category("Category1", 0));
        mainCategoryAdapter = new MainCategoryAdapter(getContext(), categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mainCategoryAdapter);
        mainCategoryAdapter.notifyDataSetChanged();
        mainCategoryAdapter.setViewItemInterface(new MainCategoryAdapter.RecyclerViewItemInterface() {
            @Override
            public void onItemClick(int position, String c) {

                catName=c;
                Toast.makeText(getContext(), position+" "+catName, Toast.LENGTH_SHORT).show();
            }
        });

        getParentFragmentManager().beginTransaction().replace(R.id.cat_fragment_container, new MainCategoryFragment(catName)).commit();


        return view;
    }
}