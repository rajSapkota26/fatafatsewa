package com.fatafatsewa.model;

public class SubCategory {
    private int id;
    private String name;
    private String imageLink;
    private int filterCategoryId;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getFilterCategoryId() {
        return filterCategoryId;
    }

    public void setFilterCategoryId(int filterCategoryId) {
        this.filterCategoryId = filterCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
