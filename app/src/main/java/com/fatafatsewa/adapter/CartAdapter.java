package com.fatafatsewa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fatafatsewa.R;
import com.fatafatsewa.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<Cart> carts;
    private int selectedItem;
    private int quantity;
    private  double price;

    private CategoryAdapterOnlyName.RecyclerViewItemInterface viewItemInterface;

    public void setViewItemInterface(CategoryAdapterOnlyName.RecyclerViewItemInterface viewItemInterface) {
        this.viewItemInterface = viewItemInterface;
    }

    public CartAdapter(Context context, List<Cart> carts,int id) {
        this.context = context;
        this.carts = carts;
        this.selectedItem=id;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_mycart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Cart cart = carts.get(position);
        quantity=cart.getItems();
        price=cart.getPrice();
        holder.pprice.setText(String.valueOf(price));
        holder.pname.setText(cart.getpName());
        holder.pItems.setText(String.valueOf(quantity));
        Glide.with(context).load(cart.getImage()).placeholder(R.drawable.img_product).into(holder.pImage);
        if (selectedItem==0){
            holder.productChecked.setChecked(false);
        }else {
            holder.productChecked.setChecked(true);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewItemInterface != null) {


                }

            }
        });
        holder.itemplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity >0){

                    quantity=quantity+1;
                    price=cart.getPrice()*quantity;
                    holder.pItems.setText(String.valueOf(quantity));
                    holder.pprice.setText(String.valueOf(price));
//                   notifyItemChanged(position);
                }

            }
        });  holder.itemMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity>1){
                    quantity=quantity-1;
                    price=cart.getPrice()*quantity;
                    holder.pItems.setText(String.valueOf(quantity));
                    holder.pprice.setText(String.valueOf(price));
//                    notifyItemChanged(position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pImage;
        TextView pname, itemplus, pprice,itemMinus,pItems;
        CheckBox productChecked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pImage = itemView.findViewById(R.id.cart_image);
            pname = itemView.findViewById(R.id.cart_name);
            pItems = itemView.findViewById(R.id.cart_items);
            itemplus = itemView.findViewById(R.id.itemplus);
            itemMinus = itemView.findViewById(R.id.itemminus);
            pprice = itemView.findViewById(R.id.cart_price);
            productChecked = itemView.findViewById(R.id.productChecked);
        }
    }

    public interface RecyclerViewItemInterface {

        void onItemClick(int position, String catName);

    }
}
