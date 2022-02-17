package com.fatafatsewa.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fatafatsewa.activity.ProductListElectronicActivity;
import com.fatafatsewa.adapter.BrandAdapter;
import com.fatafatsewa.adapter.CategoryAdapterNameWithImage;
import com.fatafatsewa.adapter.ImageSliderAdapter;
import com.fatafatsewa.adapter.ProductAdapter;
import com.fatafatsewa.adapter.BannerProductAdapter;
import com.fatafatsewa.databinding.FragmentHomeBinding;
import com.fatafatsewa.model.Brand;
import com.fatafatsewa.model.Category;
import com.fatafatsewa.model.Product;
import com.fatafatsewa.model.SliderItem;
import com.fatafatsewa.model.BannerProduct;
import com.fatafatsewa.server.api.ApiRegister;
import com.fatafatsewa.server.service.BannerProductService;
import com.fatafatsewa.server.service.BrandService;
import com.fatafatsewa.server.service.CategoryService;
import com.fatafatsewa.server.service.ProductService;
import com.fatafatsewa.server.service.SliderItemService;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    //image slider
    private ImageSliderAdapter adapter;
    private List<SliderItem> sliderItem;
    SliderItemService itemService;

    //category list
    private CategoryAdapterNameWithImage categoryAdapter;
    private List<Category> categories;
    CategoryService categoryService;
    //banner list
    private BrandAdapter brandAdapter;
    private List<Brand> brands;
    BrandService brandService;
    //auto scrool brands
    final int duration = 10;
    final int pixelsToMove = 30;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    //countdowntimer
    CountDownTimer timer;

    //sponser
    private List<BannerProduct> sponsers;
    private BannerProductAdapter sponserAdapter;
    BannerProductService bannerProductService;
    //product list
    private List<Product> products;
    private ProductAdapter productAdapter;
    ProductService productService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        //set slider
        setSliderItem();

        //load gif file
        loadgifFile();

        //category recycler view
        getCategoryListless();


        //category viewmore
        binding.categoryViewMore.setOnClickListener(view -> {
            setCategoryviewMore();
            binding.categoryViewLess.setVisibility(View.VISIBLE);
            binding.categoryViewMore.setVisibility(View.GONE);
        }); //category viewmore
        binding.categoryViewLess.setOnClickListener(view -> {
            getCategoryListless();
            binding.categoryViewLess.setVisibility(View.GONE);
            binding.categoryViewMore.setVisibility(View.VISIBLE);
        });

        //load brand list
        LoadBrandList();

        //timer for flash sell
        counter(System.currentTimeMillis() + 86400);

        //set sponser
        sponserLists();
        //show products
        setProductList();
        binding.flashshellItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), ProductListElectronicActivity.class);
                intent.putExtra("flashId",7);
                startActivity(intent);
            }
        });
        return binding.getRoot();

    }

    private void getCategoryListless() {
        categories = new ArrayList<>();
        categoryService = ApiRegister.getCategoryService();
        Call<List<Category>> catList = categoryService.getCatList();
        catList.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = response.body();
                binding.mainCategoryRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
                categoryAdapter = new CategoryAdapterNameWithImage(getContext(), categories.subList(0, 4));
                binding.mainCategoryRecyclerview.setAdapter(categoryAdapter);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("problem", "onFailure: " + t.getMessage());
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
                binding.mainProductRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
                productAdapter = new ProductAdapter(getContext(), products);
                binding.mainProductRecyclerview.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void sponserLists() {
        sponsers = new ArrayList<>();
        bannerProductService = ApiRegister.getBannerProductService();
        Call<List<BannerProduct>> serviceCatList = bannerProductService.getCatList();
        serviceCatList.enqueue(new Callback<List<BannerProduct>>() {
            @Override
            public void onResponse(Call<List<BannerProduct>> call, Response<List<BannerProduct>> response) {
                sponsers = response.body();
                binding.mainSponserRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                sponserAdapter = new BannerProductAdapter(getContext(), sponsers);
                binding.mainSponserRecyclerview.setAdapter(sponserAdapter);
                sponserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BannerProduct>> call, Throwable t) {

            }
        });

    }

    private void LoadBrandList() {
        brands = new ArrayList<>();
        brandService = ApiRegister.getBrandService();
        Call<List<Brand>> brandList = brandService.getCatList();
        brandList.enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                brands = response.body();
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.mainBrandsRecyclerview.setLayoutManager(layoutManager);
                brandAdapter = new BrandAdapter(getContext(), brands);
                binding.mainBrandsRecyclerview.setAdapter(brandAdapter);
                brandAdapter.notifyDataSetChanged();
                //set auto scrool
                binding.mainBrandsRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                        if (lastItem == layoutManager.getItemCount() - 1) {
                            mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                            Handler postHandler = new Handler();
                            postHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    binding.mainBrandsRecyclerview.setAdapter(null);
                                    binding.mainBrandsRecyclerview.setAdapter(brandAdapter);
                                    mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
                                }
                            }, 2000);
                        }
                    }
                });
                mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {

            }
        });

    }

    private void loadgifFile() {
        Glide.with(this)
                .load("https://fatafatsewa.com/storage/media/4154/oneplus-9-pro.gif")
                .into(binding.bannergif);
    }


    private void setSliderItem() {
        //slider image adapter
        sliderItem = new ArrayList<>();
        itemService = ApiRegister.getSliderItemService();
        Call<List<SliderItem>> itemList = itemService.getCatList();
        itemList.enqueue(new Callback<List<SliderItem>>() {
            @Override
            public void onResponse(Call<List<SliderItem>> call, Response<List<SliderItem>> response) {
                sliderItem = response.body();
                adapter = (new ImageSliderAdapter(getContext(), sliderItem));
                binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.DROP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                binding.imageSlider.startAutoCycle();
                binding.imageSlider.setSliderAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SliderItem>> call, Throwable t) {

            }
        });

    }


    //auto scrool bands
    private final Runnable SCROLLING_RUNNABLE = new Runnable() {

        @Override
        public void run() {
            binding.mainBrandsRecyclerview.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };


    private void setCategoryviewMore() {
        categories = new ArrayList<>();
        categoryService = ApiRegister.getCategoryService();
        Call<List<Category>> catList = categoryService.getCatList();
        catList.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = response.body();
                binding.mainCategoryRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
                categoryAdapter = new CategoryAdapterNameWithImage(getContext(), categories);
                binding.mainCategoryRecyclerview.setAdapter(categoryAdapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("problem", "onFailure: " + t.getMessage());
            }
        });


    }


    //countdown timer
    private void counter(long min) {
        timer = new CountDownTimer(min, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                binding.fleshsellTimer.setText(hours + ":" + minutes + ":" + seconds);
            }

            public void onFinish() {

            }
        };
        timer.start();
    }

}