package com.fatafatsewa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fatafatsewa.R;
import com.fatafatsewa.activity.ProductListElectronicActivity;
import com.fatafatsewa.model.Category;

import java.util.List;

public class CategoryAdapterNameWithImage extends RecyclerView.Adapter<CategoryAdapterNameWithImage.ViewHolder> {
    private Context context;
    private List<Category> categories;

    public CategoryAdapterNameWithImage(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_categorywithname,parent,false);
        return new CategoryAdapterNameWithImage.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category=categories.get(position);
        holder.cat_name.setText(category.getName());
        Glide.with(context).load(category.getImage()).placeholder(R.drawable.ic_camera).into(holder.main_cat_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ProductListElectronicActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView main_cat_image;
        TextView cat_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main_cat_image=itemView.findViewById(R.id.main_cat_image);
            cat_name=itemView.findViewById(R.id.cat_name);
        }
    }
}
