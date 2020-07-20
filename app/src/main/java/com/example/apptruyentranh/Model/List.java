package com.example.apptruyentranh.Model;

public class List {
    private String id;
    private String title;
    private String img;
    private String category;
    private String newChapter;

    public List(String id, String title, String img, String category, String newChapter) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.category = category;
        this.newChapter = newChapter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNewChapter() {
        return newChapter;
    }

    public void setNewChapter(String newChapter) {
        this.newChapter = newChapter;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
