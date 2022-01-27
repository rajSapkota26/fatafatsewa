package com.fatafatsewa.demolist;

import com.fatafatsewa.R;
import com.fatafatsewa.model.Brand;
import com.fatafatsewa.model.Category;
import com.fatafatsewa.model.Product;
import com.fatafatsewa.model.SliderItem;
import com.fatafatsewa.model.BannerProduct;
import com.fatafatsewa.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class Demolist {

    public static List<SliderItem> getAllSliderItem() {
        List<SliderItem> l = new ArrayList<>();

        l.add(new SliderItem(R.drawable.img_sliderimage, "you get Video Lecture "));
        l.add(new SliderItem(R.drawable.logo, "you get Video Lecture "));
        l.add(new SliderItem(R.drawable.img_sliderimage, "you get Video Lecture "));
        l.add(new SliderItem(R.drawable.logo, "you get Video Lecture "));
        return l;
    }

    public static List<Category> getAllcategoryItem() {
        List<Category> l = new ArrayList<>();
        l.add(new Category("Mobile", R.drawable.ic_mobile));
        l.add(new Category("Laptop", R.drawable.ic_laptop));
        l.add(new Category("Camera", R.drawable.ic_camera));
        l.add(new Category("Accessories", R.drawable.ic_accessory));
        l.add(new Category(" Machinery", R.drawable.ic_machinary));
        l.add(new Category(" Bar", R.drawable.ic_bar));
        l.add(new Category(" Appliances", R.drawable.ic_homeapplience));
        l.add(new Category("Stationary", R.drawable.ic_stationary));

        return l;
    } public static List<SubCategory> getAllsubcategoryItem() {
        List<SubCategory> l = new ArrayList<>();
        l.add(new SubCategory("Mobile", R.drawable.ic_mobile));
        l.add(new SubCategory("Laptop", R.drawable.ic_laptop));
        l.add(new SubCategory("Camera", R.drawable.ic_camera));
        l.add(new SubCategory("Accessories", R.drawable.ic_accessory));
        l.add(new SubCategory(" Machinery", R.drawable.ic_machinary));
        l.add(new SubCategory(" Bar", R.drawable.ic_bar));
        l.add(new SubCategory(" Appliances", R.drawable.ic_homeapplience));
        l.add(new SubCategory("Stationary", R.drawable.ic_stationary));

        return l;
    }

    public static List<Product> getAllProductItem() {
        List<Product> l = new ArrayList<>();
        l.add(new Product("Samsung galaxy A301", 2500.00, "samsung", R.drawable.img_product));
        l.add(new Product("product1", 2500.00, "samsung", R.drawable.img_product));
        l.add(new Product("product1", 2500.00, "samsung", R.drawable.img_product));
        l.add(new Product("product1", 2500.00, "samsung", R.drawable.img_product));
        l.add(new Product("product1", 2500.00, "samsung", R.drawable.img_product));
        l.add(new Product("product1", 2500.00, "samsung", R.drawable.img_product));
        l.add(new Product("product1", 2500.00, "samsung", R.drawable.img_product));
        l.add(new Product("product1", 2500.00, "samsung", R.drawable.img_product));
        l.add(new Product("product1", 2500.00, "samsung", R.drawable.img_product));

        return l;
    }

    public static List<Brand> getAllBrandItem() {
        List<Brand> l = new ArrayList<>();
        l.add(new Brand(R.drawable.logo));
        l.add(new Brand(R.drawable.logo));
        l.add(new Brand(R.drawable.logo));
        l.add(new Brand(R.drawable.logo));
        l.add(new Brand(R.drawable.logo));

        return l;
    }

    public static List<BannerProduct> getAllSponserItem() {
        List<BannerProduct> l = new ArrayList<>();

        l.add(new BannerProduct("Top products", R.drawable.img_brand, getAllProductItem()));
        l.add(new BannerProduct("products of the day", R.drawable.img_brand, getAllProductItem()));
        l.add(new BannerProduct("new Arriavle", R.drawable.img_brand, getAllProductItem()));
        l.add(new BannerProduct("Top products", R.drawable.img_brand, getAllProductItem()));
        l.add(new BannerProduct("Top products", R.drawable.img_brand, getAllProductItem()));


        return l;
    }
}
