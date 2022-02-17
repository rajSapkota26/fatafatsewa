package com.fatafatsewa.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fatafatsewa.R;
import com.fatafatsewa.adapter.CategoryAdapterNameWithImage;
import com.fatafatsewa.adapter.CategoryAdapterOnlyName;
import com.fatafatsewa.model.Category;
import com.fatafatsewa.server.api.ApiRegister;
import com.fatafatsewa.server.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Category> categories;
    CategoryAdapterOnlyName mainCategoryAdapter;
    private int catName = -1;
    InnerCategoryFragment mainCategoryFragment;
    CategoryService categoryService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.cat_cat_recyclerview);
        categories = new ArrayList<>();
        mainCategoryFragment = new InnerCategoryFragment(catName);
        categoryService = ApiRegister.getCategoryService();
        Call<List<Category>> catList = categoryService.getCatList();
        catList.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = response.body();
                mainCategoryAdapter = new CategoryAdapterOnlyName(getContext(), categories);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(mainCategoryAdapter);
                mainCategoryAdapter.notifyDataSetChanged();
                mainCategoryAdapter.setViewItemInterface((position, c) -> {
                    catName = c;
                    Toast.makeText(getContext(), position + " " + catName, Toast.LENGTH_SHORT).show();
                    getParentFragmentManager().beginTransaction().replace(R.id.cat_fragment_container, new InnerCategoryFragment(c)).commit();

                });

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("problem", "onFailure: " + t.getMessage());
            }
        });

        if (catName == -1) {

            getParentFragmentManager().beginTransaction().replace(R.id.cat_fragment_container, mainCategoryFragment).commit();


        }


        return view;
    }
}