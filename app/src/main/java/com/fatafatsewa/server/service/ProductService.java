package com.fatafatsewa.server.service;

import com.fatafatsewa.model.Product;
import com.fatafatsewa.model.SubCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {


    //get single filter category by id
    @GET("product/{id}")
    Call<Product> getProductById(@Path("id") int id);

    //get single filter category by id
    @GET("allproduct/subcategory/{id}")
    Call<List<Product>> getProductBySubCategoryId(@Path("id") int id);
 //get single filter category by id
    @GET("allproduct/category/{id}")
    Call<List<Product>> getProductByCategoryId(@Path("id") int id);


    //get single filter category by id
    @GET("allproduct/brand/{id}")
    Call<List<Product>> getProductByBrandId(@Path("id") int id);

    //get single filter category by id
    @GET("allproduct")
    Call<List<Product>> getAllProduct();

    //get single filter category by id

    @GET("topProduct")
    Call<List<Product>> topProduct();



    @GET("specialProduct")
    Call<List<Product>> specialProduct();

    //get single filter category by id

    @GET("offerproduct")
    Call<List<Product>> offerproduct();

    //get single filter category by id

    @GET("hotdealproduct")
    Call<List<Product>> hotdealproduct();

    //get single filter category by id

    @GET("newArriavleproduct")
    Call<List<Product>> newArriavleproduct();
}
