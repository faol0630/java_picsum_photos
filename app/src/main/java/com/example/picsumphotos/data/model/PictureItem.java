package com.example.picsumphotos.data.model;


public class PictureItem {

    private String id;

    private String author;

    private String image_url;

    //constructor

    public PictureItem(String id, String author, String image_url) {
        this.id = id;
        this.author = author;
        this.image_url = image_url;
    }

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
}

//esta clase es para usar con Mappers
