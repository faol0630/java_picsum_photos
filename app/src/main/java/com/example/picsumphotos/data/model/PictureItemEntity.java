package com.example.picsumphotos.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "picture_items_entity")
public class PictureItemEntity {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "image_url")
    private String image_url;

    @ColumnInfo(name = "width")
    private Integer width;

    @ColumnInfo(name = "height")
    private Integer height;

    //constructor
    public PictureItemEntity(@NonNull String id, String author, String image_url, Integer width, Integer height) {
        this.id = id;
        this.author = author;
        this.image_url = image_url;
        this.width = width;
        this.height = height;
    }

    //getter and setter

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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
