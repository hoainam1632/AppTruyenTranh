package com.example.apptruyentranh.slider;

public class SliderItem {
    private String id;
    private String description;
    private String imageUrl;
    public  SliderItem(String description, String imageUrl, String id){
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
