package com.fatafatsewa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.fatafatsewa.adapter.BrandAdapter;
import com.fatafatsewa.adapter.ImageSliderAdapter;
import com.fatafatsewa.adapter.ProductAdapter;
import com.fatafatsewa.adapter.SubCaregoryAdapter;
import com.fatafatsewa.databinding.ActivityProductListElectronicBinding;
import com.fatafatsewa.model.BannerProduct;
import com.fatafatsewa.model.Brand;
import com.fatafatsewa.model.Product;
import com.fatafatsewa.model.SliderItem;
import com.fatafatsewa.model.SubCategory;
import com.fatafatsewa.server.api.ApiRegister;
import com.fatafatsewa.server.service.BannerProductService;
import com.fatafatsewa.server.service.ProductService;
import com.fatafatsewa.server.service.SliderItemService;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListElectronicActivity extends AppCompatActivity {
    ActivityProductListElectronicBinding binding;
    //image slider
    private ImageSliderAdapter adapter;
    private List<SliderItem> sliderItem;
    private SliderItemService itemService;
    //product list
    private List<Product> products;
    private ProductAdapter productAdapter;
    private ProductService productService;
    private BannerProductService bannerProductService;
    int subcatId, bpId, flashId, brandId, catId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductListElectronicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        subcatId = getIntent().getIntExtra("subcatId", 0);
        bpId = getIntent().getIntExtra("bpId", 0);
        flashId = getIntent().getIntExtra("flashId", 0);
        brandId = getIntent().getIntExtra("brandId", 0);
        catId = getIntent().getIntExtra("catId", 0);
        //set slider
        setSliderItem();
        //set trending now
        if (subcatId != 0) {
            setProductList(subcatId);
        }
        if (bpId != 0) {
            loadBannerProducts(bpId);
        }
        if (brandId != 0) {
            loadBrandProducts(brandId);
        }
        if (catId != 0) {
            loadCategoryProducts(catId);
            Toast.makeText(this, ""+catId, Toast.LENGTH_SHORT).show();
        }
        if (flashId == 7) {
            loadFlashItems();
        }
        //set all product

    }

    private void loadCategoryProducts(int catId) {
        products = new ArrayList<>();
        productService = ApiRegister.getProductService();
        Call<List<Product>> productByCategoryId = productService.getProductByCategoryId(catId);
        productByCategoryId.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                binding.fatafatAllproductRecyclerview.setLayoutManager(new GridLayoutManager(ProductListElectronicActivity.this, 3));
                productAdapter = new ProductAdapter(ProductListElectronicActivity.this, products);
                binding.fatafatAllproductRecyclerview.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void loadBrandProducts(int brandId) {
        products = new ArrayList<>();
        productService = ApiRegister.getProductService();
        Call<List<Product>> productByBrandId = productService.getProductByBrandId(brandId);
        productByBrandId.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                binding.fatafatAllproductRecyclerview.setLayoutManager(new GridLayoutManager(ProductListElectronicActivity.this, 3));
                productAdapter = new ProductAdapter(ProductListElectronicActivity.this, products);
                binding.fatafatAllproductRecyclerview.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void loadFlashItems() {
        products = new ArrayList<>();
        productService = ApiRegister.getProductService();
        Call<List<Product>> offerproduct = productService.offerproduct();
        offerproduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                binding.fatafatAllproductRecyclerview.setLayoutManager(new GridLayoutManager(ProductListElectronicActivity.this, 3));
                productAdapter = new ProductAdapter(ProductListElectronicActivity.this, products);
                binding.fatafatAllproductRecyclerview.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void loadBannerProducts(int bpId) {
        products = new ArrayList<>();
        bannerProductService = ApiRegister.getBannerProductService();
        Call<BannerProduct> bps = bannerProductService.getcategory(bpId);
        bps.enqueue(new Callback<BannerProduct>() {
            @Override
            public void onResponse(Call<BannerProduct> call, Response<BannerProduct> response) {
                BannerProduct bp = response.body();
                products = bp.getProducts();
                binding.fatafatAllproductRecyclerview.setLayoutManager(new GridLayoutManager(ProductListElectronicActivity.this, 3));
                productAdapter = new ProductAdapter(ProductListElectronicActivity.this, products);
                binding.fatafatAllproductRecyclerview.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BannerProduct> call, Throwable t) {

            }
        });
    }

    private void setProductList(int subcatId) {
        products = new ArrayList<>();
        productService = ApiRegister.getProductService();
        Call<List<Product>> listCall = productService.getProductBySubCategoryId(subcatId);
        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                binding.fatafatAllproductRecyclerview.setLayoutManager(new GridLayoutManager(ProductListElectronicActivity.this, 3));
                productAdapter = new ProductAdapter(ProductListElectronicActivity.this, products);
                binding.fatafatAllproductRecyclerview.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    private void setSliderItem() {
        //slider image adapter
//slider image adapter
        sliderItem = new ArrayList<>();
        itemService = ApiRegister.getSliderItemService();
        Call<List<SliderItem>> itemList = itemService.getCatList();
        itemList.enqueue(new Callback<List<SliderItem>>() {
            @Override
            public void onResponse(Call<List<SliderItem>> call, Response<List<SliderItem>> response) {
                sliderItem = response.body();
                adapter = (new ImageSliderAdapter(ProductListElectronicActivity.this, sliderItem));
                binding.productimageSlider.setIndicatorAnimation(IndicatorAnimationType.DROP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                binding.productimageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                binding.productimageSlider.startAutoCycle();
                binding.productimageSlider.setSliderAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SliderItem>> call, Throwable t) {

            }
        });
    }
}