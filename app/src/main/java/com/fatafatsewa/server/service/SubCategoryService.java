package com.fatafatsewa.server.service;

import com.fatafatsewa.model.Category;
import com.fatafatsewa.model.FilterCategory;
import com.fatafatsewa.model.SubCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SubCategoryService {

       //get single filter category by id
    @GET("subcategory/{id}")
    Call<SubCategory> getSubCategoryById(@Path("id") int id);

    //get single filter category by id
    @GET("subcategory/category/{id}")
    Call<List<SubCategory>> getSubCategoryByFilterCategoryId(@Path("id") int id);
}
