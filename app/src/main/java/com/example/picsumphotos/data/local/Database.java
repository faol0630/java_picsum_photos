package com.example.picsumphotos.data.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.picsumphotos.data.model.PictureItemEntity;

@androidx.room.Database(entities = {PictureItemEntity.class}, version = 3)
public abstract class Database extends RoomDatabase {

    private static Database databaseInstance;

    public abstract PicturesItemLocalDAO getDAO();

    public synchronized static Database getInstance(Context context) {
        if (databaseInstance == null) {

            databaseInstance = Room
                    .databaseBuilder(context, Database.class, "picture_items_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return databaseInstance;
    }
}

//synchronized : only one thread can access this method
