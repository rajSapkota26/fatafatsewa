package com.fatafatsewa.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fatafatsewa.R;
import com.fatafatsewa.activity.OrdersActivity;
import com.fatafatsewa.adapter.CartAdapter;
import com.fatafatsewa.model.Cart;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Cart> carts;
    private CartAdapter cartAdapter;
    Button cart_checkoutnow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        carts = new ArrayList<>();
        carts.add(new Cart("pro1",2,R.drawable.img_product_two,2500));
        carts.add(new Cart("pro1",2,R.drawable.img_product_two,2500));
        carts.add(new Cart("pro1",2,R.drawable.img_product_two,2500));
        recyclerView = view.findViewById(R.id.cart_mycartRecycler);
        cart_checkoutnow = view.findViewById(R.id.cart_checkoutnow);
        setCartItem(0);

        cart_checkoutnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), OrdersActivity.class));
            }
        });


        return view;
    }

    private void setCartItem(int id) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter = new CartAdapter(getContext(), carts, id);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }
}