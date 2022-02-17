package com.fatafatsewa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fatafatsewa.R;
import com.fatafatsewa.adapter.ProductAdapter;
import com.fatafatsewa.databinding.ActivityProductDetailsBinding;
import com.fatafatsewa.databinding.ActivityProductListElectronicBinding;
import com.fatafatsewa.model.Product;
import com.fatafatsewa.server.api.ApiRegister;
import com.fatafatsewa.server.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    ActivityProductDetailsBinding binding;
    int pId;
    private ProductService productService;
    double d = 0;
    //product list
    private List<Product> products;
    private ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        pId = getIntent().getIntExtra("pId", 0);
        productService = ApiRegister.getProductService();

        Call<Product> product = productService.getProductById(pId);
        product.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product pro = response.body();
                binding.pdlProductName.setText(pro.getName());
                binding.pdlProductRate.setText("Rs:" + pro.getPrice());
                if (pro.getDiscount() > 0) {
                    binding.productDiscount.setText("Rs:" + (pro.getPrice() + pro.getDiscount()));

                } else {
                    binding.productDiscount.setVisibility(View.GONE);
                    binding.prorateview.setVisibility(View.GONE);
                }
                setColors(pro.getImageLink());
                setSpects(pro.getPrice());

                Glide.with(ProductDetailsActivity.this).load(pro.getImageLink()).placeholder(R.drawable.img_product).into(binding.pdtlProductImage);

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
        setProductList();
        binding.pdlProductService.setOnClickListener(view -> setPopupMessage("this is product services"));
        binding.pdlProductDelivary.setOnClickListener(view -> setPopupMessage("this is product delivery"));
        binding.pdlProductSpecification.setOnClickListener(view -> setPopupMessage("this is product specification"));

    }

    private void setSpects(double price) {
        List<String> varients = new ArrayList<>();
        varients.add("Default");
        varients.add("4/64");
        varients.add("4/128");
        varients.add("6/64");
        varients.add("6/128");
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter(ProductDetailsActivity.this, android.R.layout.simple_list_item_1, varients);
        myAdapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.varientSpiner.setAdapter(myAdapter1);
        binding.varientSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String string = adapterView.getItemAtPosition(i).toString();
                if (string.equals("Default")){
                    binding.pdlProductRate.setText("Rs:" + price);

                }else {
                    binding.pdlProductRate.setText("Rs:" + 150);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setColors(String imageLink) {
        List<String> colors = new ArrayList<>();
        colors.add("Default");
        colors.add("Black");
        colors.add("Red");
        colors.add("Blue");
        ArrayAdapter<String> myAdapter = new ArrayAdapter(ProductDetailsActivity.this, android.R.layout.simple_list_item_1, colors);
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.colSpiner.setAdapter(myAdapter);
        binding.colSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String string = adapterView.getItemAtPosition(i).toString();
                if (string.equals("Default")){
                    Glide.with(ProductDetailsActivity.this).load(imageLink).placeholder(R.drawable.img_product).into(binding.pdtlProductImage);

                }else {
                    Glide.with(ProductDetailsActivity.this).load(R.drawable.logo).placeholder(R.drawable.img_product).into(binding.pdtlProductImage);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//
            }
        });
    }

    private void setProductList() {
        products = new ArrayList<>();
        productService = ApiRegister.getProductService();
        Call<List<Product>> allProduct = productService.getAllProduct();
        allProduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                binding.pdlProductRecyclerview.setLayoutManager(new GridLayoutManager(ProductDetailsActivity.this, 3));
                productAdapter = new ProductAdapter(ProductDetailsActivity.this, products);
                binding.pdlProductRecyclerview.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setPopupMessage(String msg) {
        ProgressDialog myDialog;
        myDialog = new ProgressDialog(ProductDetailsActivity.this);
        myDialog.setMessage((Html.fromHtml(msg)));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            myDialog.setIndeterminateDrawable(getDrawable(R.drawable.logo));
        }
        myDialog.setCancelable(false);
        myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDialog.dismiss();//dismiss dialog
            }
        });
        myDialog.show();
    }
}