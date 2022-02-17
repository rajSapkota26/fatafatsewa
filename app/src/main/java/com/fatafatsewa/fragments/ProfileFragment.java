package com.fatafatsewa.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fatafatsewa.R;
import com.fatafatsewa.activity.AddProfileActivity;
import com.fatafatsewa.activity.OrdersActivity;
import com.fatafatsewa.activity.ViewProfileActivity;

public class ProfileFragment extends Fragment {
    TextView pf_editProfile, pf_viewProfile, pf_viewOrders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        pf_viewProfile = view.findViewById(R.id.pf_viewProfile);
        pf_editProfile = view.findViewById(R.id.pf_editProfile);
        pf_viewOrders = view.findViewById(R.id.pf_viewOrders);

        pf_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), AddProfileActivity.class));
            }
        });
        pf_viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), ViewProfileActivity.class));
            }
        });
        pf_viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), OrdersActivity.class));
            }
        });
        return view;
    }
}