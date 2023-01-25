package com.example.picsumphotos.data.model;


import java.io.Serializable;

public class PictureItem implements Serializable {

    private String id;

    private String author;

    private String image_url;

    private Integer width;

    private Integer height;

    //constructor

    public PictureItem(String id, String author, String image_url, Integer width, Integer height) {
        this.id = id;
        this.author = author;
        this.image_url = image_url;
        this.width = width;
        this.height = height;
    }

    //getter and setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage_url() {
        return image_url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
