package com.example.picsumphotos.data.local;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.picsumphotos.data.model.PictureItemEntity;

import java.util.List;

@Dao
public interface PicturesItemLocalDAO {

    @Insert(onConflict = REPLACE)
    void saveAllRoomPictureItems(List<PictureItemEntity> images);

    @Query("SELECT * FROM picture_items_entity")
    List<PictureItemEntity> getAllRoomPictureItems();
}
