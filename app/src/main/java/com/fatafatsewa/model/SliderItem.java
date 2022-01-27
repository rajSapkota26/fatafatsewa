package com.fatafatsewa.model;

public class SliderItem {
    private int imageUrl;
    private String description;

    public SliderItem() {
    }

    public SliderItem(int imageUrl, String description) {
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
