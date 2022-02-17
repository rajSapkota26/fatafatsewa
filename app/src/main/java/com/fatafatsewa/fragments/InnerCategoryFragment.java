package com.fatafatsewa.fragments;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fatafatsewa.R;
import com.fatafatsewa.adapter.CategoryAdapterNameWithImage;
import com.fatafatsewa.adapter.FilterCategoryAdapter;
import com.fatafatsewa.adapter.SubCaregoryAdapter;

import com.fatafatsewa.model.Category;
import com.fatafatsewa.model.FilterCategory;
import com.fatafatsewa.model.SubCategory;
import com.fatafatsewa.server.api.ApiRegister;
import com.fatafatsewa.server.service.CategoryService;
import com.fatafatsewa.server.service.FilterCategoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InnerCategoryFragment extends Fragment {
    private int catname;

    public InnerCategoryFragment(int catname) {
        this.catname = catname;
    }

    private RecyclerView suggestionforyou, filterCategoryRecyclerview;
    List<Category> categories;
    CategoryService categoryService;

    CategoryAdapterNameWithImage categoryAdapter;
    NestedScrollView defaultLoad, changeLoad;
    TextView changedCateName;

    List<FilterCategory> filterCategories;
    FilterCategoryAdapter filterCategoryAdapter;
    FilterCategoryService filterCategoryService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inner_category, container, false);

        suggestionforyou = view.findViewById(R.id.cat_recentlyview_recyclerview);
        filterCategoryRecyclerview = view.findViewById(R.id.cat_subcategoryfiltercategory_recyclerview);
        defaultLoad = view.findViewById(R.id.defaultpage);
        changeLoad = view.findViewById(R.id.catChange);
        changedCateName = view.findViewById(R.id.changed_category);


        if (catname == -1) {
            //set subcategory
            setDafaultsubCategory();
            changeLoad.setVisibility(View.GONE);
        } else {
            changedCateName.setText("" + catname);
            defaultLoad.setVisibility(View.GONE);
            changeLoad.setVisibility(View.VISIBLE);
            setChangedSubCategory();
        }


        return view;
    }

    private void setChangedSubCategory() {
        filterCategories = new ArrayList<>();
        filterCategoryService = ApiRegister.getFilterCatService();
        Call<List<FilterCategory>> getfiltercategory = filterCategoryService.getfiltercategory(catname);
        getfiltercategory.enqueue(new Callback<List<FilterCategory>>() {
            @Override
            public void onResponse(Call<List<FilterCategory>> call, Response<List<FilterCategory>> response) {
                filterCategories=response.body();
                filterCategoryAdapter = new FilterCategoryAdapter(getContext(), filterCategories);
                filterCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                filterCategoryRecyclerview.setAdapter(filterCategoryAdapter);
                filterCategoryAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<FilterCategory>> call, Throwable t) {

            }
        });

    }

    private void setDafaultsubCategory() {
        categories = new ArrayList<>();
        categoryService = ApiRegister.getCategoryService();
        Call<List<Category>> catList = categoryService.getCatList();
        catList.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = response.body();
                suggestionforyou.setLayoutManager(new GridLayoutManager(getContext(), 3));
                categoryAdapter = new CategoryAdapterNameWithImage(getContext(), categories);
                suggestionforyou.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });


    }
}