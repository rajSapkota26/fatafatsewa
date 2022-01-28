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

import java.util.List;

public class FilterCategoryAdapter extends RecyclerView.Adapter<FilterCategoryAdapter.ViewHolder> {
    private Context context;
    private List<FilterCategory> filterCategoryList;
    private SubCaregoryAdapter subCaregoryAdapter;

    public FilterCategoryAdapter(Context context, List<FilterCategory> filterCategoryList) {
        this.context = context;
        this.filterCategoryList = filterCategoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_filter_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FilterCategory filterCategory=filterCategoryList.get(position);
        subCaregoryAdapter=new SubCaregoryAdapter(context,filterCategory.getProducts());
        holder.catName.setText(filterCategory.getName());

        holder.expandmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(context).load(filterCategory.getImage()).placeholder(R.drawable.ic_camera).into(holder.sponser_img_brand);
                holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
                holder.recyclerView.setAdapter(subCaregoryAdapter);
                subCaregoryAdapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return filterCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView expandmore,catName;
        ImageView sponser_img_brand;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catName=itemView.findViewById(R.id.subcategory_cat_name);
            expandmore=itemView.findViewById(R.id.category_expand);
            recyclerView=itemView.findViewById(R.id.filter_category_recycler_view);
            sponser_img_brand=itemView.findViewById(R.id.category_banner_img_brand);
        }
    }
}
