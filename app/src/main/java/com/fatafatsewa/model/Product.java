package com.fatafatsewa.model;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String description;
    private String specification;
    private int brandId;
    private int subcatId;

    private boolean hotdeal;
    private boolean newArriavle;
    private boolean specialProduct;
    private boolean offerProduct;
    private boolean topProduct;
    private String imageLink;
    private double price;
    private double discount;

    private List<ProductColor> colors;

    private List<ProductSpec> specs;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getSubcatId() {
        return subcatId;
    }

    public void setSubcatId(int subcatId) {
        this.subcatId = subcatId;
    }

    public boolean isHotdeal() {
        return hotdeal;
    }

    public void setHotdeal(boolean hotdeal) {
        this.hotdeal = hotdeal;
    }

    public boolean isNewArriavle() {
        return newArriavle;
    }

    public void setNewArriavle(boolean newArriavle) {
        this.newArriavle = newArriavle;
    }

    public boolean isSpecialProduct() {
        return specialProduct;
    }

    public void setSpecialProduct(boolean specialProduct) {
        this.specialProduct = specialProduct;
    }

    public boolean isOfferProduct() {
        return offerProduct;
    }

    public void setOfferProduct(boolean offerProduct) {
        this.offerProduct = offerProduct;
    }

    public boolean isTopProduct() {
        return topProduct;
    }

    public void setTopProduct(boolean topProduct) {
        this.topProduct = topProduct;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<ProductColor> getColors() {
        return colors;
    }

    public void setColors(List<ProductColor> colors) {
        this.colors = colors;
    }

    public List<ProductSpec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<ProductSpec> specs) {
        this.specs = specs;
    }
}
