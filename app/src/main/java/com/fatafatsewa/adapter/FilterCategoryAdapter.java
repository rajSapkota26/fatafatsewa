package com.fatafatsewa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fatafatsewa.R;
import com.fatafatsewa.model.FilterCategory;
import com.fatafatsewa.model.SubCategory;
import com.fatafatsewa.server.api.ApiRegister;
import com.fatafatsewa.server.service.SubCategoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterCategoryAdapter extends RecyclerView.Adapter<FilterCategoryAdapter.ViewHolder> {
    private Context context;
    private List<FilterCategory> filterCategoryList;
    private SubCaregoryAdapter subCaregoryAdapter;
    private SubCategoryService subCategoryService;
    List<SubCategory> subCategories;


    public FilterCategoryAdapter(Context context, List<FilterCategory> filterCategoryList) {
        this.context = context;
        this.filterCategoryList = filterCategoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_filter_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FilterCategory filterCategory = filterCategoryList.get(position);


//
        holder.catName.setText(filterCategory.getName());
        holder.sponser_img_brand.setVisibility(View.GONE);

        holder.expandmore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                holder.sponser_img_brand.setVisibility(View.VISIBLE);
                Glide.with(context).load(filterCategory.getImageLink()).placeholder(R.drawable.ic_camera).into(holder.sponser_img_brand);
                loadSubcategory(filterCategory.getId(), holder);


            }
        });


    }

    private void loadSubcategory(int id, ViewHolder holder) {
        subCategoryService = ApiRegister.getSubCategoryService();
        subCategories = new ArrayList<>();
        Call<List<SubCategory>> subCategoryCall = subCategoryService.getSubCategoryByFilterCategoryId(id);
        subCategoryCall.enqueue(new Callback<List<SubCategory>>() {
            @Override
            public void onResponse(Call<List<SubCategory>> call, Response<List<SubCategory>> response) {
                subCategories =  response.body();
                subCaregoryAdapter = new SubCaregoryAdapter(context, subCategories);
                holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                holder.recyclerView.setAdapter(subCaregoryAdapter);
                subCaregoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SubCategory>> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return filterCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView expandmore, catName;
        ImageView sponser_img_brand;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.subcategory_cat_name);
            expandmore = itemView.findViewById(R.id.category_expand);
            recyclerView = itemView.findViewById(R.id.filter_category_recycler_view);
            sponser_img_brand = itemView.findViewById(R.id.category_banner_img_brand);
        }
    }
}
