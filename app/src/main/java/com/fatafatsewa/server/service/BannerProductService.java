package com.fatafatsewa.server.service;

import com.fatafatsewa.model.BannerProduct;
import com.fatafatsewa.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BannerProductService {
    @GET("bannerProduct/")
    Call<List<BannerProduct>> getCatList();

    //get single category by id
    @GET("bannerProduct/{id}")
    Call<BannerProduct> getcategory(@Path("id") int id);
}
