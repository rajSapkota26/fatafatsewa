package com.fatafatsewa.server.service;

import com.fatafatsewa.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {
    //get all category list
    @GET("category/")
    Call<List<Category>> getCatList();

    //get single category by id
    @GET("category/{id}")
    Call<Category> getcategory(@Path("id") int id);
}
