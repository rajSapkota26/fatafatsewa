package com.fatafatsewa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fatafatsewa.R;
import com.fatafatsewa.model.Category;
import com.fatafatsewa.model.SubCategory;

import java.util.List;

public class SubCaregoryAdapter extends RecyclerView.Adapter<SubCaregoryAdapter.ViewHolder> {
    private Context context;
    private List<SubCategory> subCategories;

    public SubCaregoryAdapter(Context context, List<SubCategory> subCategories) {
        this.context = context;
        this.subCategories = subCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_sub_category, parent, false);
        return new SubCaregoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubCategory category=subCategories.get(position);
        holder.name.setText(category.getName());
        Glide.with(context).load(category.getImage()).placeholder(R.drawable.ic_camera).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.main_sub_cat_image);
            name=itemView.findViewById(R.id.catsub_name);
        }
    }
}
