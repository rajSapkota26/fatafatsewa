package com.fatafatsewa.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fatafatsewa.R;
import com.fatafatsewa.adapter.CategoryAdapterNameWithImage;
import com.fatafatsewa.adapter.SubCaregoryAdapter;
import com.fatafatsewa.demolist.Demolist;
import com.fatafatsewa.model.Category;
import com.fatafatsewa.model.SubCategory;

import java.util.List;


public class InnerCategoryFragment extends Fragment {
    private String catname;

    public InnerCategoryFragment(String catname) {
        this.catname = catname;
    }

    //product list
    private List<SubCategory> subCategories;
    private SubCaregoryAdapter subCaregoryAdapter;
    private RecyclerView recentlyAdded, suggestionforyou,onchangeRecyclerview;
    List<Category> categories;
    CategoryAdapterNameWithImage categoryAdapter;
    LinearLayout defaultLoad,changeLoad;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inner_category, container, false);
        recentlyAdded = view.findViewById(R.id.cat_subcategory_recyclerview);
        suggestionforyou = view.findViewById(R.id.cat_recentlyview_recyclerview);
        onchangeRecyclerview = view.findViewById(R.id.cat_subcategorychangewithcategory_recyclerview);
        defaultLoad = view.findViewById(R.id.defaultpage);
        changeLoad = view.findViewById(R.id.catChange);
        Toast.makeText(getContext(), catname, Toast.LENGTH_SHORT).show();

        if (catname.isEmpty()){
            //set subcategory
            setDafaultsubCategory();
            changeLoad.setVisibility(View.GONE);
        }else {

            defaultLoad.setVisibility(View.GONE);
            changeLoad.setVisibility(View.VISIBLE);
            setChangedSubCategory();
        }




        return view;
    }

    private void setChangedSubCategory() {
        subCategories = Demolist.getAllsubcategoryItem();

        subCaregoryAdapter = new SubCaregoryAdapter(getContext(), subCategories);
        onchangeRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        onchangeRecyclerview.setAdapter(subCaregoryAdapter);
        subCaregoryAdapter.notifyDataSetChanged();
    }

    private void setDafaultsubCategory() {
        subCategories = Demolist.getAllsubcategoryItem();

        subCaregoryAdapter = new SubCaregoryAdapter(getContext(), subCategories);
        recentlyAdded.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recentlyAdded.setAdapter(subCaregoryAdapter);
        subCaregoryAdapter.notifyDataSetChanged();

        categories = Demolist.getAllcategoryItem();

        suggestionforyou.setLayoutManager(new GridLayoutManager(getContext(), 3));
        categoryAdapter = new CategoryAdapterNameWithImage(getContext(), categories);
        suggestionforyou.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }
}