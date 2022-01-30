package com.fatafatsewa.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fatafatsewa.R;
import com.fatafatsewa.adapter.CartAdapter;
import com.fatafatsewa.demolist.Demolist;
import com.fatafatsewa.model.Cart;

import java.util.List;


public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Cart> carts;
    private CartAdapter cartAdapter;
    CheckBox cat_selectAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        carts = Demolist.getAllcartItem();
        recyclerView = view.findViewById(R.id.cart_mycartRecycler);
        cat_selectAll = view.findViewById(R.id.cat_selectAll);
        setCartItem(0);
        cat_selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    setCartItem(1);
                }else {
                    setCartItem(0);
                }
            }
        });




        return view;
    }

    private void setCartItem(int id) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter=new CartAdapter(getContext(),carts,id);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }
}