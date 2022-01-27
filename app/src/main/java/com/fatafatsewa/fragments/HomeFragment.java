package com.fatafatsewa.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.fatafatsewa.R;
import com.fatafatsewa.adapter.BrandAdapter;
import com.fatafatsewa.adapter.CategoryAdapter;
import com.fatafatsewa.adapter.ImageSliderAdapter;
import com.fatafatsewa.adapter.ProductAdapter;
import com.fatafatsewa.adapter.SponserAdapter;
import com.fatafatsewa.databinding.FragmentHomeBinding;
import com.fatafatsewa.demolist.Demolist;
import com.fatafatsewa.model.Brand;
import com.fatafatsewa.model.Category;
import com.fatafatsewa.model.Product;
import com.fatafatsewa.model.SliderItem;
import com.fatafatsewa.model.Sponser;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    //image slider
    private ImageSliderAdapter adapter;
    private List<SliderItem> sliderItem;

    //category list
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;
    //banner list
    private BrandAdapter brandAdapter;
    private List<Brand> brands;
    //auto scrool brands
    final int duration = 10;
    final int pixelsToMove = 30;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
   //countdowntimer
   CountDownTimer timer;

   //sponser
   private List<Sponser> sponsers;
   private SponserAdapter sponserAdapter;
   //product list
    private List<Product> products;
    private ProductAdapter productAdapter;
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
        categories = Demolist.getAllcategoryItem();
        setCategoryviewless(categories);

        //category viewmore
        binding.categoryViewMore.setOnClickListener(view -> {
            setCategoryviewMore(categories);
            binding.categoryViewLess.setVisibility(View.VISIBLE);
            binding.categoryViewMore.setVisibility(View.GONE);
        }); //category viewmore
        binding.categoryViewLess.setOnClickListener(view -> {
            setCategoryviewless(categories);
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

        return binding.getRoot();

    }

    private void setProductList() {
        products=Demolist.getAllProductItem();
        binding.mainProductRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));
        productAdapter=new ProductAdapter(getContext(),products);
        binding.mainProductRecyclerview.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }

    private void sponserLists() {
        sponsers=Demolist.getAllSponserItem();
        binding.mainSponserRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        sponserAdapter=new SponserAdapter(getContext(),sponsers);
        binding.mainSponserRecyclerview.setAdapter(sponserAdapter);
        sponserAdapter.notifyDataSetChanged();
    }

    private void LoadBrandList() {
        brands = Demolist.getAllBrandItem();
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

    private void loadgifFile() {
        Glide.with(this)
                .load("https://fatafatsewa.com/storage/media/4154/oneplus-9-pro.gif")
                .into(binding.bannergif);
    }


    private void setSliderItem() {
        //slider image adapter
        sliderItem = Demolist.getAllSliderItem();
        adapter = (new ImageSliderAdapter(getContext(), sliderItem));
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.DROP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider.startAutoCycle();
        binding.imageSlider.setSliderAdapter(adapter);
    }


    //auto scrool bands
    private final Runnable SCROLLING_RUNNABLE = new Runnable() {

        @Override
        public void run() {
            binding.mainBrandsRecyclerview.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };


    private void setCategoryviewMore(List<Category> c) {
        binding.mainCategoryRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        categoryAdapter = new CategoryAdapter(getContext(), c);
        binding.mainCategoryRecyclerview.setAdapter(categoryAdapter);
        adapter.notifyDataSetChanged();
    }


    private void setCategoryviewless(List<Category> c) {
        c = categories.subList(0, 4);
        binding.mainCategoryRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        categoryAdapter = new CategoryAdapter(getContext(), c);
        binding.mainCategoryRecyclerview.setAdapter(categoryAdapter);
        adapter.notifyDataSetChanged();
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