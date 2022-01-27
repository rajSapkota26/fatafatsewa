package com.fatafatsewa.model;

import java.util.List;

public class FilterCategory {
    private String name;
    private List<Product> products;
    private int image;

    public FilterCategory(String name, int image, List<Product> products) {
        this.name = name;
        this.products = products;
        this.image = image;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
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
