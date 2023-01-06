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

//    public PictureItem(String id, String author, String image_url) {
//        this.id = id;
//        this.author = author;
//        this.image_url = image_url;
//    }


    //getter and setter
    //(para ser usados en mapper, fromModel)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

//esta clase es para usar con Mappers
