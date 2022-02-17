package com.fatafatsewa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatafatsewa.R;
import com.fatafatsewa.model.Category;

import java.util.List;

import static com.fatafatsewa.R.color.color_white;

public class CategoryAdapterOnlyName extends RecyclerView.Adapter<CategoryAdapterOnlyName.ViewHolder> {
    private Context context;
    private List<Category> categories;
    private RecyclerViewItemInterface viewItemInterface;
    private int selectedItem;

    public void setViewItemInterface(RecyclerViewItemInterface viewItemInterface) {
        this.viewItemInterface = viewItemInterface;
    }

    public RecyclerViewItemInterface getViewItemInterface() {
        return viewItemInterface;
    }

    public CategoryAdapterOnlyName(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
        selectedItem=-1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Category category = categories.get(position);
        holder.name.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewItemInterface != null) {

                    int previousItem = selectedItem;
                    selectedItem = position;

                    notifyItemChanged(previousItem);
                    notifyItemChanged(position);

                    viewItemInterface.onItemClick(holder.getLayoutPosition(), category.getId());
                }
            }
        });
        if (selectedItem == position) {
            holder.name.setTextColor(context.getResources().getColor(R.color.orange));
        }else {
            holder.name.setTextColor(context.getResources().getColor(R.color.black));
        }






    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout linearLayout_sc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.catName);
            linearLayout_sc = itemView.findViewById(R.id.linearLayout_sc);
        }
    }

    public interface RecyclerViewItemInterface {

        void onItemClick(int position, int catId);

    }
}
