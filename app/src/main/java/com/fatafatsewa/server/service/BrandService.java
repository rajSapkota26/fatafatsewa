package com.fatafatsewa.server.service;

import com.fatafatsewa.model.Brand;
import com.fatafatsewa.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BrandService {
    //get all category list
    @GET("brand")
    Call<List<Brand>> getCatList();

    //get single category by id
    @GET("brand/{id}")
    Call<Brand> getcategory(@Path("id") int id);
}
