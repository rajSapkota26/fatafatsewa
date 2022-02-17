package com.fatafatsewa.model;

import java.util.List;

public class BannerProduct {
    private int id;
    private String name;
    private List<Product> products;
    private String imageLink;

    public BannerProduct(int id, String name, List<Product> products, String imageLink) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.imageLink = imageLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
