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
import com.fatafatsewa.demolist.Demolist;
import com.fatafatsewa.model.Category;
import com.fatafatsewa.model.FilterCategory;
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
    private RecyclerView recentlyAdded, suggestionforyou, onchangeRecyclerview,filterCategoryRecyclerview;
    List<Category> categories;
    CategoryAdapterNameWithImage categoryAdapter;
    NestedScrollView defaultLoad, changeLoad;
    TextView changedCateName;
    List<FilterCategory> filterCategories;
    FilterCategoryAdapter filterCategoryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inner_category, container, false);
        recentlyAdded = view.findViewById(R.id.cat_subcategory_recyclerview);
        suggestionforyou = view.findViewById(R.id.cat_recentlyview_recyclerview);
        onchangeRecyclerview = view.findViewById(R.id.cat_subcategorychangewithcategory_recyclerview);
        filterCategoryRecyclerview = view.findViewById(R.id.cat_subcategoryfiltercategory_recyclerview);
        defaultLoad = view.findViewById(R.id.defaultpage);
        changeLoad = view.findViewById(R.id.catChange);
        changedCateName = view.findViewById(R.id.changed_category);
        Toast.makeText(getContext(), catname, Toast.LENGTH_SHORT).show();

        if (catname.isEmpty()) {
            //set subcategory
            setDafaultsubCategory();
            changeLoad.setVisibility(View.GONE);
        } else {
            changedCateName.setText(catname);
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

        filterCategories=Demolist.getAllfilterItem();
        filterCategoryAdapter=new FilterCategoryAdapter(getContext(),filterCategories);
        filterCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        filterCategoryRecyclerview.setAdapter(filterCategoryAdapter);
        filterCategoryAdapter.notifyDataSetChanged();

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