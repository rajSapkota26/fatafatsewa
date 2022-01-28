package com.fatafatsewa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.fatafatsewa.R;
import com.fatafatsewa.adapter.BrandAdapter;
import com.fatafatsewa.adapter.CategoryAdapterNameWithImage;
import com.fatafatsewa.adapter.ImageSliderAdapter;
import com.fatafatsewa.adapter.ProductAdapter;
import com.fatafatsewa.adapter.SubCaregoryAdapter;
import com.fatafatsewa.databinding.ActivityFatafatBinding;
import com.fatafatsewa.databinding.ActivityProductDetailsBinding;
import com.fatafatsewa.databinding.ActivityProductListElectronicBinding;
import com.fatafatsewa.demolist.Demolist;
import com.fatafatsewa.model.Brand;
import com.fatafatsewa.model.Product;
import com.fatafatsewa.model.SliderItem;
import com.fatafatsewa.model.SubCategory;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.List;

public class ProductListElectronicActivity extends AppCompatActivity {
    ActivityProductListElectronicBinding binding;
    //image slider
    private ImageSliderAdapter adapter;
    private List<SliderItem> sliderItem;

    //product list
    private List<Product> products;
    private ProductAdapter productAdapter;
    //banner list
    private BrandAdapter brandAdapter;
    private List<Brand> brands;
    //subcategory
    private List<SubCategory> subCategories;
    private SubCaregoryAdapter subCaregoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductListElectronicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //set slider
        setSliderItem();
        //set trending now
        setTrendingBrand();
        //set fatafat mahadeal
        setMahaDeal();
        //set shop by brands
        LoadBrandList();
        //set flagship model
        setDafaultsubCategory();
        //set set budget model
        setbudgetDeal();
        //set shop by price
        subByPrice();
        //set all product
        setProductList();
    }

    private void subByPrice() {
        products=Demolist.getAllProductItem();
        binding.fatafatBypriceRecyclerview.setLayoutManager(new LinearLayoutManager(ProductListElectronicActivity.this,LinearLayoutManager.HORIZONTAL,false));
        productAdapter=new ProductAdapter(ProductListElectronicActivity.this,products);
        binding.fatafatBypriceRecyclerview.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }

    private void setbudgetDeal() {
        products=Demolist.getAllProductItem();
        binding.fatafatBudgetRecyclerview.setLayoutManager(new LinearLayoutManager(ProductListElectronicActivity.this,LinearLayoutManager.HORIZONTAL,false));
        productAdapter=new ProductAdapter(ProductListElectronicActivity.this,products);
        binding.fatafatBudgetRecyclerview.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }

    private void setDafaultsubCategory() {
        subCategories = Demolist.getAllsubcategoryItem();

        subCaregoryAdapter = new SubCaregoryAdapter(ProductListElectronicActivity.this, subCategories.subList(0,6));
        binding.fatafatFlagshipRecyclerview.setLayoutManager(new GridLayoutManager(ProductListElectronicActivity.this, 3));
        binding.fatafatFlagshipRecyclerview.setAdapter(subCaregoryAdapter);
        subCaregoryAdapter.notifyDataSetChanged();
    }

    private void LoadBrandList() {
        brands = Demolist.getAllBrandItem();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(ProductListElectronicActivity.this, LinearLayoutManager.HORIZONTAL, false);
        binding.fatafatShopbybrandRecyclerview.setLayoutManager(layoutManager);
        brandAdapter = new BrandAdapter(ProductListElectronicActivity.this, brands);
        binding.fatafatShopbybrandRecyclerview.setAdapter(brandAdapter);
        brandAdapter.notifyDataSetChanged();
//
    }

    private void setMahaDeal() {
        products=Demolist.getAllProductItem();
        binding.fatafatDealsRecyclerview.setLayoutManager(new GridLayoutManager(ProductListElectronicActivity.this,2));
        productAdapter=new ProductAdapter(ProductListElectronicActivity.this,products.subList(0,4));
        binding.fatafatDealsRecyclerview.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }

    private void setTrendingBrand() {
        products=Demolist.getAllProductItem();
        binding.trendingRecyclerview.setLayoutManager(new LinearLayoutManager(ProductListElectronicActivity.this,LinearLayoutManager.HORIZONTAL,false));
        productAdapter=new ProductAdapter(ProductListElectronicActivity.this,products);
        binding.trendingRecyclerview.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }
    private void setProductList() {
        products=Demolist.getAllProductItem();
        binding.fatafatAllproductRecyclerview.setLayoutManager(new GridLayoutManager(ProductListElectronicActivity.this,3));
        productAdapter=new ProductAdapter(ProductListElectronicActivity.this,products);
        binding.fatafatAllproductRecyclerview.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }
    private void setSliderItem() {
        //slider image adapter
        sliderItem = Demolist.getAllSliderItem();
        adapter = (new ImageSliderAdapter(ProductListElectronicActivity.this, sliderItem));
        binding.productimageSlider.setIndicatorAnimation(IndicatorAnimationType.DROP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.productimageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.productimageSlider.startAutoCycle();
        binding.productimageSlider.setSliderAdapter(adapter);
    }
}