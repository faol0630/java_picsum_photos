package com.example.picsumphotos.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "picture_items")
public class PictureItemEntity {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "image_url")
    private String image_url;

    //constructor
    public PictureItemEntity(String id, String author, String image_url) {
        this.id = id;
        this.author = author;
        this.image_url = image_url;
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
