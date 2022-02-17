package com.fatafatsewa.server.service;

import com.fatafatsewa.model.Category;
import com.fatafatsewa.model.FilterCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilterCategoryService {

    //get single filter category by id
    @GET("filterCategory/category/{id}")
    Call<List<FilterCategory>> getfiltercategory(@Path("id") int id);

    //get single filter category by id
    @GET("filterCategory/{id}")
    Call<FilterCategory> getfiltercategorybyid(@Path("id") int id);
}
