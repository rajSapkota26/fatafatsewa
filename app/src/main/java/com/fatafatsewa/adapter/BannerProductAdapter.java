package com.fatafatsewa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fatafatsewa.R;
import com.fatafatsewa.model.BannerProduct;

import java.util.List;

public class BannerProductAdapter extends RecyclerView.Adapter<BannerProductAdapter.ViewHolder>{
    private Context context;
    private List<BannerProduct> sponsers;
   private ProductAdapter adapter;

    public BannerProductAdapter(Context context, List<BannerProduct> sponsers) {
        this.context = context;
        this.sponsers = sponsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_banner_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BannerProduct sponser=sponsers.get(position);
        adapter=new ProductAdapter(context,sponser.getProducts());
        holder.catName.setText(sponser.getName());
        Glide.with(context).load(sponser.getImage()).placeholder(R.drawable.img_product).into(holder.sponser_img_brand);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return sponsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sellAll,catName;
        ImageView sponser_img_brand;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catName=itemView.findViewById(R.id.sponser_cat_name);
            sellAll=itemView.findViewById(R.id.sponser_see_all);
            recyclerView=itemView.findViewById(R.id.sponser_recycler_view);
            sponser_img_brand=itemView.findViewById(R.id.sponser_img_brand);
        }
    }
}
