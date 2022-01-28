package com.fatafatsewa.model;

import java.util.List;

public class FilterCategory {
    private String name;
    private List<SubCategory> products;
    private int image;

    public FilterCategory(String name, int image, List<SubCategory> products) {
        this.name = name;
        this.products = products;
        this.image = image;
    }

    public List<SubCategory> getProducts() {
        return products;
    }

    public void setProducts(List<SubCategory> products) {
        this.products = products;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
