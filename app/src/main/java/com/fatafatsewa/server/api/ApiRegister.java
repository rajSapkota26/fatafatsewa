package com.fatafatsewa.server.api;


import com.fatafatsewa.server.service.BannerProductService;
import com.fatafatsewa.server.service.BrandService;
import com.fatafatsewa.server.service.CategoryService;
import com.fatafatsewa.server.service.FilterCategoryService;
import com.fatafatsewa.server.service.ProductService;
import com.fatafatsewa.server.service.SliderItemService;
import com.fatafatsewa.server.service.SubCategoryService;

public class ApiRegister {
    public static final String URL_001 = "http://192.168.1.72:8081/";
    private static RetrofitInstance Client;


    public static CategoryService getCategoryService() {
        return Client.getClient(URL_001).create(CategoryService.class);
    }
    public static FilterCategoryService getFilterCatService() {
        return Client.getClient(URL_001).create(FilterCategoryService.class);
    }

    public static BrandService getBrandService() {
        return Client.getClient(URL_001).create(BrandService.class);
    }
    public static SubCategoryService getSubCategoryService() {
        return Client.getClient(URL_001).create(SubCategoryService.class);
    }
    public static ProductService getProductService() {
        return Client.getClient(URL_001).create(ProductService.class);
    }

    public static BannerProductService getBannerProductService() {
        return Client.getClient(URL_001).create(BannerProductService.class);
    }

    public static SliderItemService getSliderItemService() {
        return Client.getClient(URL_001).create(SliderItemService.class);
    }
}
