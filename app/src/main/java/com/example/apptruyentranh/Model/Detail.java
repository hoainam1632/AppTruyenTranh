package com.example.apptruyentranh.Model;

public class Detail {
    private String id;
    private String chapter;

    public Detail(String id, String chapter) {
        this.id = id;
        this.chapter = chapter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }


}
