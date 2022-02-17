package com.fatafatsewa.server.service;

import com.fatafatsewa.model.Category;
import com.fatafatsewa.model.SliderItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SliderItemService {
    @GET("sliderItem/")
    Call<List<SliderItem>> getCatList();

    //get single category by id
    @GET("sliderItem/{id}")
    Call<SliderItem> getcategory(@Path("id") int id);
}
