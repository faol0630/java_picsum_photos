package com.example.picsumphotos.data.model;

import com.google.gson.annotations.SerializedName;

public class PictureItemDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("download_url")
    private String image_url;

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
